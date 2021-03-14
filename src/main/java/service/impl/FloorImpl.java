package service.impl;

import dto.AddVehicleResponse;
import models.ParkingFloor;
import models.ParkingSlot;
import models.Ticket;
import models.Vehicle;
import service.IFloor;
import stratergy.IParkingStratergy;
import stratergy.impl.NormalParkingStratergy;

import java.util.*;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class FloorImpl implements IFloor {

    Map<Integer, ParkingFloor> floorMap;
    int ticketCounter = 0;
    int floors;
    IParkingStratergy iParkingStratergy;

    public boolean createSystem(int floors) {
        floorMap = new HashMap<Integer, ParkingFloor>();
        this.floors = floors;
        for(int i=0;i<floors;i++){
            ParkingFloor parkingFloor = new ParkingFloor();
            List<ParkingSlot> slotsList = new ArrayList<ParkingSlot>();
            for(int j=1;j<=20;j++){
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotNo(j);
                slotsList.add(slot);
            }
            parkingFloor.setSlots(slotsList);
            parkingFloor.setFloorNumber(i+1);
            floorMap.put(i+1, parkingFloor);
        }
        return true;
    }

    public synchronized Ticket addVehicle(Vehicle vehicle){

        Ticket ticket = new Ticket();
        ticketCounter++;
        ticket.setTicketId(ticketCounter);

        checkAvailibilty(vehicle, ticket);
        if(ticket.getSlotNo() != 0){
            return ticket;
        }
        else{
            return null;
        }
    }

    private Ticket formTicket(Vehicle vehicle, ParkingSlot slot, Ticket ticket, Integer floorNo){

        ticket.setEntryTime(System.currentTimeMillis());
        ticket.setFloorNo(floorNo);
        ticket.setSlotNo(slot.getSlotNo());
        ticket.setVehicleId(vehicle.getVehicleId());

        if(!slot.isBottomFull() ){
            ticket.setBottom(true);

        }else if(!slot.isTopFull()){
            ticket.setBottom(false);
        }
        return ticket;
    }

    private void checkAvailibilty(Vehicle vehicle, Ticket ticket){

        switch (vehicle.getPassengerType()){
            //floor 1 and 2 reserved for elderly
            case ELDERLY:

                for(Map.Entry<Integer, ParkingFloor> i: floorMap.entrySet()){
                    if(i.getValue().isFull()){
                        continue;
                    }

                    for (ParkingSlot slot: i.getValue().getSlots()){
                        Ticket ticketAlloted = formTicket(vehicle, slot, ticket, i.getValue().getFloorNumber());
//                        if(slot.getSlotNo() == i.getValue().getSlots().size()-1 && slot.isBottomFull() && slot.isTopFull()){
//                            i.getValue().setFull(true);
//                        }


                        if(ticketAlloted!=null){
                            i.getValue().setParkingFull();
                            ticket = ticketAlloted;
                            return;
                        }
                    }
                }

                break;
            case NORMAL:

//                use floor val instead of map for iteration
                iParkingStratergy = new NormalParkingStratergy();
                Iterator<Map.Entry<Integer, ParkingFloor>> floorItr = floorMap.entrySet().iterator();

                for(int i=1;i<=2;i++){
                    floorItr.next();
                }


                while(floorItr.hasNext())
                {
                    Map.Entry<Integer, ParkingFloor> i = floorItr.next();
                    if(i.getValue().isFull()){
                        continue;
                    }

                    List<ParkingSlot> slots = i.getValue().getSlots();

                    ParkingSlot slot = iParkingStratergy.add(slots);

                    if(slot ==null) {
                        continue;
                    }
                    Ticket ticketAlloted = formTicket(vehicle, slot, ticket, i.getValue().getFloorNumber());

                    i.getValue().setParkingFull();
                    if(ticketAlloted!=null){
                        ticket = ticketAlloted;
                        return;
                    }
                }

                Iterator<Map.Entry<Integer, ParkingFloor>> itrNew = floorMap.entrySet().iterator();

                for(int i=1;i<=2;i++){
                    itrNew.next();
                }


                while(itrNew.hasNext())
                {

                    Map.Entry<Integer, ParkingFloor> i = itrNew.next();
                    if(i.getValue().isFull()){
                        continue;
                    }

                    List<ParkingSlot> slots = i.getValue().getSlots();

                    ParkingSlot slot = iParkingStratergy.add(slots);

                    if(slot ==null) {
                        continue;
                    }

                    Ticket ticketAlloted = formTicket(vehicle, slot, ticket, i.getValue().getFloorNumber());

                    i.getValue().setParkingFull();
                    if(ticketAlloted!=null){
                        ticket = ticketAlloted;
                        return;
                    }
                }

                break;
            case ROYAL:
                Iterator<Map.Entry<Integer, ParkingFloor>> itr = floorMap.entrySet().iterator();

                for(int i=1;i<=2;i++){
                    itr.next();
                }


                while(itr.hasNext())
                {
                    Map.Entry<Integer, ParkingFloor> i = itr.next();
                    if(i.getValue().isFull()){
                        continue;
                    }

                    List<ParkingSlot> slots = i.getValue().getSlots();

                    for (int slot=0;slot<2;slot++){
                        ParkingSlot currentSlot = slots.get(slot);

                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());

                        i.getValue().setParkingFull();
                        if(ticketAlloted!=null){
                            ticket = ticketAlloted;
                            return;
                        }
                    }
                }

                Iterator<Map.Entry<Integer, ParkingFloor>> itrSecond = floorMap.entrySet().iterator();

                for(int i=1;i<=2;i++){
                    itrSecond.next();
                }


                while(itrSecond.hasNext())
                {


                    Map.Entry<Integer, ParkingFloor> i = itrSecond.next();
                    if(i.getValue().isFull()){
                        continue;
                    }

                    List<ParkingSlot> slots = i.getValue().getSlots();

                    for (int slot=2;slot<slots.size();slot++){
                        ParkingSlot currentSlot = slots.get(slot);
                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());

                        i.getValue().setParkingFull();
                        if(ticketAlloted!=null){
                            ticket = ticketAlloted;
                            return;
                        }
                    }

                }


                break;
        }
        return;
    }

    public boolean removeVehicle(Ticket ticket){

        if(ticket.getTicketId() > ticketCounter){
            return false;
        }

        ParkingFloor floor = floorMap.get(ticket.getFloorNo());
        if(ticket != null){
            if(ticket.isBottom()){
                floor.getSlots().get(ticket.getSlotNo()-1).setBottomFull(false);
            }else{
                floor.getSlots().get(ticket.getSlotNo()-1).setTopFull(false);
            }
        }
        floor.updateParkingFull();
        ticket.setExitTime(System.currentTimeMillis());
        int cost = (int)(ticket.getExitTime()-ticket.getEntryTime()+10)*20;
        cost = (int) Math.ceil(cost);
        ticket.setCost(cost);

        System.out.println(floor.toString());
        System.out.println("after "+ ticket.toString());

        return true;
    }

}

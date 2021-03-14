package service.impl;

import dto.AddVehicleResponse;
import models.ParkingFloor;
import models.ParkingSlot;
import models.Ticket;
import models.Vehicle;
import service.IFloor;

import java.util.*;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class IFloorImpl implements IFloor {

    Map<Integer, ParkingFloor> floorMap;
    int ticketCounter = 0;

    public boolean createSystem(int floors) {
        floorMap = new HashMap<Integer, ParkingFloor>();
        for(int i=0;i<floors;i++){
            ParkingFloor parkingFloor = new ParkingFloor();
            List<ParkingSlot> slotsList = new ArrayList<ParkingSlot>();
            for(int j=1;j<=3;j++){
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

    public Ticket addVehicle(Vehicle vehicle){

        Ticket ticket = new Ticket();
        ticketCounter++;
        ticket.setTicketId(ticketCounter);

        checkAvailibilty(vehicle, ticket);
        return ticket;
    }

    private Ticket formTicket(Vehicle vehicle, ParkingSlot slot, Ticket ticket, Integer floorNo){

        ticket.setEntryTime(System.currentTimeMillis());
        ticket.setFloorNo(floorNo);
        ticket.setSlotNo(slot.getSlotNo());
        ticket.setVehicleId(vehicle.getVehicleId());

        if(!slot.isBottomFull() ){
            slot.setBottomFull(true);
            ticket.setBottom(true);

        }else if(!slot.isTopFull()){
            slot.setTopFull(true);
            ticket.setBottom(false);
        }
        else {
            return null;
        }
        return ticket;
    }

    private Ticket checkAvailibilty(Vehicle vehicle, Ticket ticket){

        switch (vehicle.getPassengerType()){
            //floor 1 and 2 reserved for elderly
            case ELDERLY:

                for(Map.Entry<Integer, ParkingFloor> i: floorMap.entrySet()){

                    for (ParkingSlot slot: i.getValue().getSlots()){
                        Ticket ticketAlloted = formTicket(vehicle, slot, ticket, i.getValue().getFloorNumber());
                        if(slot.getSlotNo() == i.getValue().getSlots().size()-1 && slot.isBottomFull() && slot.isTopFull()){
                            i.getValue().setFull(true);
                        }

                        if(ticketAlloted!=null){
                            ticket = ticketAlloted;
                            return ticket;
                        }
                    }
                }

                break;
            case NORMAL:

                Iterator<Map.Entry<Integer, ParkingFloor>> itr = floorMap.entrySet().iterator();

                for(int i=1;i<=2;i++){
                    itr.next();
                }


                while(itr.hasNext())
                {
                    Map.Entry<Integer, ParkingFloor> i = itr.next();

                    List<ParkingSlot> slots = i.getValue().getSlots();

                    for (int slot=0;slot<2;slot++){
                        ParkingSlot currentSlot = slots.get(slot);
                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());

                        if(slot == slots.size()-1 && currentSlot.isBottomFull() && currentSlot.isTopFull()){
                            i.getValue().setFull(true);
                        }

                        if(ticketAlloted!=null){
                            ticket = ticketAlloted;
                            return ticket;
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
                    List<ParkingSlot> slots = i.getValue().getSlots();

                    for (int slot=2;slot<slots.size();slot++){
                        ParkingSlot currentSlot = slots.get(slot);
                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());

                        if(slot == slots.size()-1 && currentSlot.isBottomFull() && currentSlot.isTopFull()){
                            i.getValue().setFull(true);
                        }

                        if(ticketAlloted!=null){
                            ticket = ticketAlloted;
                            return ticket;
                        }
                    }

                }
                break;
            case ROYAL:

//                Iterator<Map.Entry<Integer, ParkingFloor>> itrOne = floorMap.entrySet().iterator();
//
//                for(int i=1;i<=2;i++){
//                    itrOne.next();
//                }
//
//
//                while(itrOne.hasNext())
//                {
//                    Map.Entry<Integer, ParkingFloor> i = itrOne.next();
//
//                    List<ParkingSlot> slots = i.getValue().getSlots();
//
//                    for (int slot=0;slot<2;slot++){
//                        ParkingSlot currentSlot = slots.get(slot);
//                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());
//
//                        if(slot == slots.size()-1){
//                            continue;
//                        }else if(slot == slots.size()-2){
//                            ticketAlloted = formTicket(vehicle, slots.get(slot+1), ticket, i.getValue().getFloorNumber());
//                        }else{
//
//                        }
//
//                        if(slot == slots.size()-1 && currentSlot.isBottomFull() && currentSlot.isTopFull()){
//                            i.getValue().setFull(true);
//                        }
//
//                        if(ticketAlloted!=null){
//                            ticket = ticketAlloted;
//                            return ticket;
//                        }
//                    }
//                }
//
//                Iterator<Map.Entry<Integer, ParkingFloor>> itrNew = floorMap.entrySet().iterator();
//
//                for(int i=1;i<=2;i++){
//                    itrNew.next();
//                }
//
//
//                while(itrNew.hasNext())
//                {
//
//
//                    Map.Entry<Integer, ParkingFloor> i = itrNew.next();
//                    List<ParkingSlot> slots = i.getValue().getSlots();
//
//                    for (int slot=2;slot<slots.size();slot++){
//                        ParkingSlot currentSlot = slots.get(slot);
//                        Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());
//
//                        if(slot == slots.size()-1){
//                            continue;
//                        }
//
//                        if(slot == slots.size()-1 && currentSlot.isBottomFull() && currentSlot.isTopFull()){
//                            i.getValue().setFull(true);
//                        }
//
//                        if(ticketAlloted!=null){
//                            ticket = ticketAlloted;
//                            return ticket;
//                        }
//                    }
//
//                }


                break;
        }
        return ticket;
    }

    public boolean removeVehicle(Ticket ticket){

        if(ticket.getTicketId() > ticketCounter){
            return false;
        }

        ParkingFloor floor = floorMap.get(ticket.getFloorNo());
        if(ticket.isBottom()){
            floor.getSlots().get(ticket.getSlotNo()-1).setBottomFull(false);
        }else{
            floor.getSlots().get(ticket.getSlotNo()-1).setTopFull(false);
        }

        //clear full floor val
        ticket.setExitTime(System.currentTimeMillis());
        int cost = (int)(ticket.getExitTime()-ticket.getEntryTime()+10)*20;
        cost = (int) Math.ceil(cost);
        ticket.setCost(cost);

        System.out.println(floor.toString());
        System.out.println("after "+ ticket.toString());

        return true;
    }

}

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
            for(int j=1;j<=4;j++){
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

        for(Map.Entry<Integer, ParkingFloor> i: floorMap.entrySet()){

            List<ParkingSlot> slots = i.getValue().getSlots();

            for (int slot=0;slot<2;slot++){
                ParkingSlot currentSlot = slots.get(slot);
                Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());
                if(ticketAlloted!=null){
                    ticket = ticketAlloted;
                    return ticket;
                }
            }
        }

        for(Map.Entry<Integer, ParkingFloor> i: floorMap.entrySet()){

            List<ParkingSlot> slots = i.getValue().getSlots();

            for (int slot=2;slot<slots.size();slot++){
                ParkingSlot currentSlot = slots.get(slot);
                Ticket ticketAlloted = formTicket(vehicle, currentSlot, ticket, i.getValue().getFloorNumber());
                if(ticketAlloted!=null){
                    ticket = ticketAlloted;
                    return ticket;
                }
            }
        }

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
        ticket.setExitTime(System.currentTimeMillis());
        int cost = (int)(ticket.getExitTime()-ticket.getEntryTime()+10)*20;
        cost = (int) Math.ceil(cost);
        ticket.setCost(cost);

        System.out.println(floor.toString());
        System.out.println("after "+ ticket.toString());

        return true;
    }

}

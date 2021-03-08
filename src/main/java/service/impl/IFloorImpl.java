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

    public Ticket addVehicle(Vehicle vehicle){

        Ticket ticket = new Ticket();
        ticketCounter++;
        ticket.setTicketId(ticketCounter);

        for(Map.Entry<Integer, ParkingFloor> i: floorMap.entrySet()){

            for (ParkingSlot slot: i.getValue().getSlots()){
                if(!slot.isBottomFull() ){
                    slot.setBottomFull(true);
                    ticket.setEntryTime(System.currentTimeMillis());
                    ticket.setFloorNo(i.getValue().getFloorNumber());
                    ticket.setSlotNo(slot.getSlotNo());
                    ticket.setVehicleId(vehicle.getVehicleId());
                    return ticket;

                }else if(!slot.isTopFull()){
                    slot.setTopFull(true);
                    ticket.setEntryTime(System.currentTimeMillis());
                    ticket.setFloorNo(i.getValue().getFloorNumber());
                    ticket.setSlotNo(slot.getSlotNo());
                    ticket.setVehicleId(vehicle.getVehicleId());
                    return ticket;
                }
            }
        }

        return ticket;
    }

    public boolean removeVehicle(Ticket ticket){

//        Ticket ticket = new Ticket();
//        ticketCounter++;
//        ticket.setTicketId(ticketCounter);

        if(ticket.getTicketId() > ticketCounter){
            return false;
        }

        ParkingFloor floor = floorMap.get(ticket.getFloorNo());
        floor.getSlots().get(ticket.getSlotNo()).setBottomFull(false);
        ticket.setExitTime(System.currentTimeMillis());
        int cost = (int)(ticket.getExitTime()-ticket.getEntryTime())/60000;
        cost = (int) Math.ceil(cost);
        ticket.setCost(cost);

        return true;
    }

}

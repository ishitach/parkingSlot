package dao;

import models.ParkingFloor;
import models.ParkingSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class DatabaseService {

    private Map<Integer, ParkingFloor> floorMap;
    private int floors;
    private static DatabaseService databaseService;

    private DatabaseService(int floors){
        floorMap = new HashMap<Integer, ParkingFloor>();
        this.floors = floors;
        for(int i=0;i<floors;i++) {
            ParkingFloor parkingFloor = new ParkingFloor();
            List<ParkingSlot> slotsList = new ArrayList<ParkingSlot>();
            for (int j = 1; j <= 20; j++) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotNo(j);
                slotsList.add(slot);
            }
            parkingFloor.setSlots(slotsList);
            parkingFloor.setFloorNumber(i + 1);
            floorMap.put(i + 1, parkingFloor);
        }

    }

    public static DatabaseService getInstance(int floors){

        if(databaseService == null){
            databaseService = new DatabaseService(floors);
        }
        return databaseService;
    }

    public Map<Integer, ParkingFloor> getFloorMap(){
        return floorMap;
    }
}

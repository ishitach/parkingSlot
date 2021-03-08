package models;

import dto.AddVehicleResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

public class ParkingSystem {

    List<ParkingFloor> parkingFloors;

    private static ParkingSystem parkingSystem;

    private ParkingSystem(int floors){
        parkingFloors = new ArrayList<ParkingFloor>(floors);
    }

    public static ParkingSystem getInstance(int floors){

        if(parkingSystem == null){
            parkingSystem = new ParkingSystem(floors);
        }
        return parkingSystem;
    }

}

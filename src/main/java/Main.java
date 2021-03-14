import constants.Passengers;
import models.ParkingSystem;
import models.Ticket;
import models.Vehicle;
import processor.RequestProcessor;
import processor.impl.RequestProcessorImpl;

import java.util.Scanner;

import static constants.Vehicles.CAR;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class Main {



    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
//        int n = s.nextInt();
//        ParkingSystem system = ParkingSystem.getInstance(n);
//        Assumption - floors are always greater than 2
        int floors = 3;

        Vehicle v1 = Vehicle.builder().passengerType(Passengers.ELDERLY).vehicleId(1).vehicleNumber("MP092728").vehicleType(CAR).build();
        Vehicle v2 = Vehicle.builder().passengerType(Passengers.NORMAL).vehicleId(1).vehicleNumber("MP092728").vehicleType(CAR).build();
        Vehicle v3 = Vehicle.builder().passengerType(Passengers.ROYAL).vehicleId(1).vehicleNumber("MP092728").vehicleType(CAR).build();

//        system.addVehicle(v1);
        RequestProcessor requestProcessor = new RequestProcessorImpl();
        requestProcessor.createParkingSystem(floors);

//        Ticket t2 = (Ticket) requestProcessor.parkingRequest(0, v1);
        Ticket t1 = new Ticket();
        for(int i=0;i<4;i++){
            t1 = (Ticket) requestProcessor.parkingRequest(0, v2);
            if(t1 == null){
                System.out.println("Parking is full");
            }
            else {
                System.out.println(t1.toString());
            }
        }

        for(int i=0;i<3;i++){
            t1 = (Ticket) requestProcessor.parkingRequest(0, v3);
            System.out.println(t1.toString());
        }

        requestProcessor.parkingRequest(1,t1);

    }

}

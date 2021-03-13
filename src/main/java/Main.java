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
        int floors = 2;

        Vehicle v1 = Vehicle.builder().passengerType(Passengers.NORMAL).vehicleId(1).vehicleNumber("MP092728").vehicleType(CAR).build();

//        system.addVehicle(v1);
        RequestProcessor requestProcessor = new RequestProcessorImpl();
        requestProcessor.createParkingSystem(floors);

//        Ticket t2 = (Ticket) requestProcessor.parkingRequest(0, v1);
        Ticket t1 = new Ticket();
        for(int i=0;i<15;i++){
            t1 = (Ticket) requestProcessor.parkingRequest(0, v1);
            System.out.println(t1.toString());

        }

        System.out.println("before "+ t1.toString());
        requestProcessor.parkingRequest(1,t1);

    }

}

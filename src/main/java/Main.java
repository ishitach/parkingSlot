import constants.Passengers;
import models.ParkingSystem;
import models.Ticket;
import models.Vehicle;
import processor.RequestProcessor;
import processor.impl.RequestProcessorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static constants.Vehicles.CAR;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class Main {



    public static void main(String[] args) throws IOException {

        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));

//        Assumption - floors are always greater than 2
        int floors  = 3;

        Vehicle v1 = Vehicle.builder().passengerType(Passengers.ELDERLY).vehicleId(1).vehicleNumber("MP092728").vehicleType(CAR).build();
        Vehicle v2 = Vehicle.builder().passengerType(Passengers.NORMAL).vehicleId(2).vehicleNumber("MP192728").vehicleType(CAR).build();
        Vehicle v3 = Vehicle.builder().passengerType(Passengers.ROYAL).vehicleId(3).vehicleNumber("MP094528").vehicleType(CAR).build();
        Vehicle v4 = Vehicle.builder().passengerType(Passengers.NORMAL).vehicleId(4).vehicleNumber("MP095728").vehicleType(CAR).build();

        RequestProcessor requestProcessor = new RequestProcessorImpl();
        requestProcessor.createParkingSystem(floors);

        Ticket t1 = (Ticket) requestProcessor.parkingRequest(0, v1);
        System.out.println(t1);

        Ticket t2 = (Ticket) requestProcessor.parkingRequest(0, v2);
        System.out.println(t2);

        Ticket t3 = (Ticket) requestProcessor.parkingRequest(0, v3);
        System.out.println(t3);

        Ticket t4 = (Ticket) requestProcessor.parkingRequest(0, v4);
        System.out.println(t4);


        requestProcessor.parkingRequest(1,t1);
        requestProcessor.parkingRequest(1,t2);
        requestProcessor.parkingRequest(1,t3);
        requestProcessor.parkingRequest(1,t4);


    }

}

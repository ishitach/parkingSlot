package processor.impl;

import models.Ticket;
import models.Vehicle;
import processor.RequestProcessor;
import service.impl.FloorImpl;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class RequestProcessorImpl implements RequestProcessor {

    FloorImpl floor;

    private static RequestProcessorImpl requestProcessor;

    public boolean createParkingSystem(int floors){
        floor = new FloorImpl();
        return floor.createSystem(floors);
    }

    public synchronized Object parkingRequest(int input, Object object) {

        switch (input){

            case 0 :
                 return floor.addVehicle((Vehicle) object);

            case 1 :
                return floor.removeVehicle((Ticket)object);

            default: break;
        }

        return null;
    }

}

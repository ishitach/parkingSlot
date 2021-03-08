package processor.impl;

import models.Ticket;
import models.Vehicle;
import processor.RequestProcessor;
import service.impl.IFloorImpl;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class RequestProcessorImpl implements RequestProcessor {

    IFloorImpl floor;


    public boolean createParkingSystem(int floors){
        floor = new IFloorImpl();
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

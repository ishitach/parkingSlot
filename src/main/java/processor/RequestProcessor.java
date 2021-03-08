package processor;

import models.Ticket;
import models.Vehicle;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public interface RequestProcessor  {

    public boolean createParkingSystem(int floors);

    public Object parkingRequest(int input, Object object);

}

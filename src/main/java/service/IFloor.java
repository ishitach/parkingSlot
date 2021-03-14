package service;

import models.Ticket;
import models.Vehicle;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public interface IFloor {

    public void createSystem(int floors);

    public Ticket addVehicle(Vehicle vehicle);

    public boolean removeVehicle(Ticket ticket);
}

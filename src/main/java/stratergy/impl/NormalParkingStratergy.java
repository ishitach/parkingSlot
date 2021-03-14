package stratergy.impl;

import models.ParkingSlot;
import models.Ticket;
import stratergy.IParkingStratergy;

import java.util.List;

/**
 * Created by ishita.chourasia on 14/03/21.
 */
public class NormalParkingStratergy implements IParkingStratergy {

    public ParkingSlot add(List<ParkingSlot> slots) {

        for (int slot=0;slot<20;slot++) {
            ParkingSlot currentSlot = slots.get(slot);
            if (!currentSlot.isBottomFull()) {
                currentSlot.setBottomFull(true);
                return currentSlot;
            } else if (!currentSlot.isTopFull()) {
                currentSlot.setTopFull(true);
                return currentSlot;
            }
        }
        return null;
    }

}

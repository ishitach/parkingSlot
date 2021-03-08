package models;

import constants.Passengers;
import constants.Vehicles;
import lombok.Data;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

@Data
public class ParkingSlot {

    int slotNo;

    boolean nearEntryOrExit;

    boolean isTopFull;

    boolean isBottomFull;

    Vehicles vehicleType;

    Passengers reservationType;
}

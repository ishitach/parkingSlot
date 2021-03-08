package dto;

import lombok.Data;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

@Data
public class AddVehicleResponse {

    boolean spaceAvailable;

    int parkingSlot;

    int parkingFloor;

    int ticketId;
}

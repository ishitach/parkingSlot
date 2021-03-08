package models;

import lombok.Data;

import java.util.List;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

@Data
public class ParkingFloor {

    int floorNumber;

    List<ParkingSlot> slots;

    boolean isFull;

}

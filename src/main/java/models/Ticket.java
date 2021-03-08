package models;

import lombok.Data;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

@Data
public class Ticket {

    int ticketId;

    Long entryTime;

    Long exitTime;

    int cost;

    int vehicleId;

    int floorNo;

    int slotNo;


}

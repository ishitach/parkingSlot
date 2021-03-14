package stratergy.impl;

import models.ParkingSlot;
import stratergy.IParkingStratergy;

import java.util.List;

/**
 * Created by ishita.chourasia on 14/03/21.
 */
public class RoyalParkingStratergy implements IParkingStratergy {

    public ParkingSlot add(List<ParkingSlot> slots) {

        for (int slot=0;slot<20;slot++) {
            ParkingSlot currentSlot = slots.get(slot);
            if (!currentSlot.isBottomFull() && !currentSlot.isTopFull()) {

                if(currentSlot.getSlotNo()%5 ==1){
                    ParkingSlot nextSlot = slots.get(slot+1);
                    if(!nextSlot.isBottomFull() && !nextSlot.isTopFull()){
                        currentSlot.setTopFull(true);
                        currentSlot.setBottomFull(true);
                        nextSlot.setBottomFull(true);
                        nextSlot.setBottomFull(false);
                        return currentSlot;
                    }
                }

                else if(currentSlot.getSlotNo()%5 ==0){
                    ParkingSlot prevSlot = slots.get(slot-1);
                    if(!prevSlot.isBottomFull() && !prevSlot.isTopFull()){
                        currentSlot.setTopFull(true);
                        currentSlot.setBottomFull(true);
                        prevSlot.setBottomFull(true);
                        prevSlot.setBottomFull(false);
                        return currentSlot;
                    }
                }
                else{
                    ParkingSlot prevSlot = slots.get(slot-1);
                    ParkingSlot nextSlot = slots.get(slot+1);
                    if(!nextSlot.isBottomFull() && !nextSlot.isTopFull() && !prevSlot.isBottomFull() && !prevSlot.isTopFull()){
                        currentSlot.setTopFull(true);
                        currentSlot.setBottomFull(true);
                        prevSlot.setBottomFull(true);
                        prevSlot.setBottomFull(false);
                        nextSlot.setBottomFull(true);
                        nextSlot.setBottomFull(false);
                        return currentSlot;
                    }

                }
            }
        }
        return null;
    }
}

package models;

import constants.Vehicles;
import lombok.Data;

import java.util.Map;

/**
 * Created by ishita.chourasia on 07/03/21.
 */

@Data
public class Fare {

    Map<Vehicles, Integer> fares;
}

package models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Gaurav
 * 14/05/23
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
@ToString
public class Vehicle {
    private String vehicleNumber;
    private VehicleType vehicleType;
}

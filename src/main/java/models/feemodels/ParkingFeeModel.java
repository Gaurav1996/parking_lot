package models.feemodels;

import models.VehicleType;

import java.time.Duration;

/**
 * @author Gaurav
 * 14/05/23
 */
public interface ParkingFeeModel {
    Long calculateFees(VehicleType vehicleType, Duration duration);
}

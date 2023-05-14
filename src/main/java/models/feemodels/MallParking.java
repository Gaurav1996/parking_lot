package models.feemodels;

import models.VehicleType;

import java.time.Duration;

/**
 * @author Gaurav
 * 14/05/23
 */
public class MallParking implements ParkingFeeModel {

    @Override
    public Long calculateFees(VehicleType vehicleType, Duration duration) {

        long fee;
        long minutes = duration.toMinutes();
        long hours = (long) Math.ceil(minutes/60.0);
        switch (vehicleType) {

            case MOTORCYCLE:
                fee = 10 * hours;
                break;

            case CAR:
                fee = 20 * hours;
                break;

            case BUS:
                fee = 50 * hours;
                break;
            default:
                throw new RuntimeException("Invalid vehicle type");
        }
        return fee;
    }
}

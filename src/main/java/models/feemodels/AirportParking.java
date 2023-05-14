package models.feemodels;

import models.VehicleType;

import java.time.Duration;

/**
 * @author Gaurav
 * 14/05/23
 */
public class AirportParking implements ParkingFeeModel {

    @Override
    public Long calculateFees(VehicleType vehicleType, Duration duration) {

        long fee;
        long minutes = duration.toMinutes();
        long hours = (long) (minutes/60.0);
        minutes = minutes%60;
        switch(vehicleType) {

            case MOTORCYCLE:
                if(hours < 1) {
                    fee = 0;
                } else if(hours < 8) {
                    fee = 40;
                } else if(hours < 24) {
                    fee = 60;
                } else {
                    long days = hours/24;
                    fee = 80 * days;
                    if(hours%24 != 0) {
                        fee += 80;
                    }
                }
                break;

            case CAR:
                if(hours < 12) {
                    fee = 60;
                } else if(hours < 24) {
                    fee = 80;
                } else {
                    long days = hours/24;
                    fee = 100 * days;
                    if(hours%24 != 0) {
                        fee += 100;
                    }
                }
                break;

            default:
                throw new RuntimeException("Vehicle type not supported in the stadium");
        }
        return fee;
    }
}

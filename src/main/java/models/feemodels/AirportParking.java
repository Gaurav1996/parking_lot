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
        long hours = duration.toHours();
        switch(vehicleType) {

            case MOTORCYCLE:
                if(hours < 1) {
                    fee = 0;
                } else if(hours < 8) {
                    fee = 40;
                } else if(hours < 24) {
                    fee = 60;
                } else {
                    fee = 80 * (hours / 24);
                }
                break;

            case CAR:
                if(hours < 12) {
                    fee = 60;
                } else if(hours < 24) {
                    fee = 80;
                } else {
                    fee = 100 * (hours / 24);
                }
                break;

            default:
                throw new RuntimeException("Vehicle type not supported in the stadium");
        }
        return fee;
    }
}

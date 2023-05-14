package models.feemodels;

import models.VehicleType;

import java.time.Duration;

/**
 * @author Gaurav
 * 14/05/23
 */
public class StadiumParking implements ParkingFeeModel{

    @Override
    public Long calculateFees(VehicleType vehicleType, Duration duration) {

        long fee;
        long minutes = duration.toMinutes();
        long hours = minutes/60 + minutes%60;
        switch(vehicleType) {

            case MOTORCYCLE:
                if(hours < 4) {
                    fee = 30;
                }
                else if(hours < 12) {
                    fee = 60;
                }
                else {
                    fee = 60 + 100 * (hours - 12);
                }
                break;

            case CAR:
                if(hours < 4) {
                    fee = 60;
                } else if(hours < 12) {
                    fee = 120;
                } else {
                    fee = 120 + 200 * (hours - 12);
                }
                break;

            default:
                throw new RuntimeException("Vehicle type not supported in the stadium");
        }
        return fee;
    }

}

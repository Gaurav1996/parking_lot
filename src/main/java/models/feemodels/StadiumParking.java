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

        long fee = 0;
        long minutes = duration.toMinutes();
        long hours = (long) (minutes/60.0);
        minutes = minutes%60;
        switch(vehicleType) {
            case MOTORCYCLE:
                if(hours > 0 || minutes > 0) {
                    fee += 30;
                    if(hours < 4) {
                        hours = 0;
                        minutes = 0;
                    }
                    else {
                        hours = hours - 4;
                    }
                }
                if(hours > 0 || minutes > 0) {
                    fee += 60;//60 for next 8 hours
                    if(hours < 8) {
                        hours = 0;
                        minutes = 0;
                    }
                    else {
                        hours = hours - 8;
                    }
                }
                if(hours > 0 || minutes > 0) {
                    fee += (100 * hours);
                    if(minutes > 0) {
                        fee += 100;
                    }
                }
                break;

            case CAR:
                if(hours > 0 || minutes > 0) {
                    fee += 60;
                    if(hours < 4) {
                        hours = 0;
                        minutes = 0;
                    }
                    else {
                        hours = hours - 4;
                    }
                }
                if(hours > 0 || minutes > 0) {
                    fee += 120;//120 for next 8 hours
                    if(hours < 8) {
                        hours = 0;
                        minutes = 0;
                    }
                    else {
                        hours = hours - 8;
                    }
                }
                if(hours > 0 || minutes > 0) {
                    fee += (200 * hours);
                    if(minutes > 0) {
                        fee += 200;
                    }
                }
                break;

            default:
                throw new RuntimeException("Vehicle type not supported in the stadium");
        }
        return fee;
    }

}

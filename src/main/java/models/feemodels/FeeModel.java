package models.feemodels;

/**
 * @author Gaurav
 * 14/05/23
 */
public enum FeeModel {
    MALL {
        @Override
        public ParkingFeeModel getParkingFeeModel() {
            return new MallParking();
        }
    },
    STADIUM {
        @Override
        public ParkingFeeModel getParkingFeeModel() {
            return new StadiumParking();
        }
    },
    AIRPORT {
        @Override
        public ParkingFeeModel getParkingFeeModel() {
            return new AirportParking();
        }
    };

    public abstract ParkingFeeModel getParkingFeeModel();
}

import models.ParkingReceipt;
import models.feemodels.FeeModel;
import models.ParkingTicket;
import models.Vehicle;
import models.VehicleType;

import java.time.LocalDateTime;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingLotManagement {
    public static void main(String[] args) {

        //Example 1
        ParkingLot parkingLot = new ParkingLot(2,0,0, FeeModel.MALL);

        Vehicle vehicle1 = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket1 = parkingLot.park(vehicle1, entryTime);
        System.out.println(ticket1);

        Vehicle vehicle2 = new Vehicle("M-02", VehicleType.MOTORCYCLE);
        entryTime = LocalDateTime.of(2022, 5, 29, 14, 44, 7);
        ParkingTicket ticket2 = parkingLot.park(vehicle2, entryTime);
        System.out.println(ticket2);


        Vehicle vehicleEx = new Vehicle("M-03", VehicleType.MOTORCYCLE);
        entryTime = LocalDateTime.of(2022, 5, 29, 14, 44, 7);
        ParkingTicket ticketEx = parkingLot.park(vehicleEx, entryTime);
        System.out.println(ticketEx != null ? ticketEx:"");

        LocalDateTime exitTime = LocalDateTime.of(2022, 5, 29, 15, 40, 7);
        ParkingReceipt parkingReceipt = parkingLot.unpark(vehicle2, exitTime);
        System.out.println(parkingReceipt);

        Vehicle vehicle3 = new Vehicle("M-03", VehicleType.MOTORCYCLE);
        entryTime = LocalDateTime.of(2022, 5, 29, 15, 59, 7);
        ParkingTicket ticket3 = parkingLot.park(vehicle3, entryTime);
        System.out.println(ticket3);

        exitTime = LocalDateTime.of(2022, 5, 29, 17, 44, 7);
        parkingReceipt = parkingLot.unpark(vehicle1, exitTime);
        System.out.println(parkingReceipt);
    }
}

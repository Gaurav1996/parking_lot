import models.ParkingReceipt;
import models.ParkingTicket;
import models.Vehicle;
import models.VehicleType;
import models.feemodels.FeeModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Gaurav
 * 15/05/23
 */
class StadiumParkingLotTest {

    private static ParkingLot parkingLot;

    @BeforeAll
    static void setUp() {
        parkingLot = new ParkingLot(1000,1500,0, FeeModel.STADIUM);
    }

    @Test
    void scooter_3H_40M_30RS() {
        Vehicle scooter1 = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime scooter1EntryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);

        ParkingTicket scooter1Ticket = parkingLot.park(scooter1, scooter1EntryTime);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, scooter1Ticket.getParkingSpot().getSpotNumber());
        assertEquals(scooter1EntryTime, scooter1Ticket.getEntryDateTime());

        //Assert fee for 3 hours 40 mins
        LocalDateTime scooter1ExitTime = scooter1EntryTime.plusHours(3).plusMinutes(40);
        ParkingReceipt scooter1Receipt = parkingLot.unpark(scooter1, scooter1ExitTime);
        Long expectedFees = 30L;
        assertEquals(scooter1EntryTime, scooter1Receipt.getEntryTime());
        assertEquals(scooter1ExitTime, scooter1Receipt.getExitTime());
        assertEquals(expectedFees, scooter1Receipt.getParkingFee());
    }

    @Test
    void scooter_14H_59M_390RS() {
        Vehicle scooter = new Vehicle("M-02", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        assertEquals(1, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());

        //Assert fee for 14 hours 59 mins
        LocalDateTime exitTime = entryTime.plusHours(14).plusMinutes(59);
        ParkingReceipt receipt = parkingLot.unpark(scooter, exitTime);
        Long expectedFees = 390L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void car_11H_30M_180RS() {
        Vehicle car = new Vehicle("C-01", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(car, entryTime);

        //Assert parking ticket
        int expectedSpotNumber = 1001;
        assertEquals(expectedSpotNumber, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());
        //Assert fee for 11 hours 30 mins
        LocalDateTime exitTime = entryTime.plusHours(11).plusMinutes(30);
        ParkingReceipt receipt = parkingLot.unpark(car, exitTime);
        Long expectedFees = 180L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }
    @Test
    void car_13H_5M_580RS() {
        Vehicle car = new Vehicle("C-02", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(car, entryTime);

        //Assert parking ticket
        assertEquals(1001, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());
        //Assert fee for 13 hours 5 mins
        LocalDateTime exitTime = entryTime.plusHours(13).plusMinutes(5);
        ParkingReceipt receipt = parkingLot.unpark(car, exitTime);
        Long expectedFees = 580L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }
}

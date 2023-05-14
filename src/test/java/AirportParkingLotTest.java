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
class AirportParkingLotTest {

    private static ParkingLot parkingLot;

    @BeforeAll
    static void setUp() {
        parkingLot = new ParkingLot(200,500,100, FeeModel.AIRPORT);
    }

    @Test
    void scooter_55M_0RS() {
        Vehicle scooter = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);

        ParkingTicket ticket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());

        //Assert fee for 55 mins
        LocalDateTime exitTime = entryTime.plusHours(0).plusMinutes(55);
        ParkingReceipt receipt = parkingLot.unpark(scooter, exitTime);
        Long expectedFees = 0L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void scooter_14H_59M_60RS() {
        Vehicle scooter = new Vehicle("M-02", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        assertEquals(1, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());

        //Assert fee for 14 hours 59 mins
        LocalDateTime exitTime = entryTime.plusHours(14).plusMinutes(59);
        ParkingReceipt receipt = parkingLot.unpark(scooter, exitTime);
        Long expectedFees = 60L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void scooter_1D_12H_160RS() {
        Vehicle scooter = new Vehicle("M-03", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        assertEquals(1, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());

        //Assert fee for 1 day 12 hours
        LocalDateTime exitTime = entryTime.plusDays(1).plusHours(12);
        ParkingReceipt receipt = parkingLot.unpark(scooter, exitTime);
        Long expectedFees = 160L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void car_50M_60RS() {
        Vehicle car = new Vehicle("C-01", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(car, entryTime);

        //Assert parking ticket
        int expectedSpotNumber = 201;
        assertEquals(expectedSpotNumber, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());
        //Assert fee for 50 mins
        LocalDateTime exitTime = entryTime.plusHours(0).plusMinutes(50);
        ParkingReceipt receipt = parkingLot.unpark(car, exitTime);
        Long expectedFees = 60L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void car_23H_59M_80RS() {
        Vehicle car = new Vehicle("C-02", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(car, entryTime);

        //Assert parking ticket
        assertEquals(201, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());
        //Assert fee for 23 hours 59 mins
        LocalDateTime exitTime = entryTime.plusHours(23).plusMinutes(59);
        ParkingReceipt receipt = parkingLot.unpark(car, exitTime);
        Long expectedFees = 80L;
        assertEquals(entryTime, receipt.getEntryTime());
        assertEquals(exitTime, receipt.getExitTime());
        assertEquals(expectedFees, receipt.getParkingFee());
    }

    @Test
    void car_3D_1H_400RS() {
        Vehicle car = new Vehicle("C-03", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket ticket = parkingLot.park(car, entryTime);

        //Assert parking ticket
        assertEquals(201, ticket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, ticket.getEntryDateTime());
        //Assert fee for 3 days 1 hours
        LocalDateTime exitTime = entryTime.plusDays(3).plusHours(1);
        ParkingReceipt car3Receipt = parkingLot.unpark(car, exitTime);
        Long expectedFees = 400L;
        assertEquals(entryTime, car3Receipt.getEntryTime());
        assertEquals(exitTime, car3Receipt.getExitTime());
        assertEquals(expectedFees, car3Receipt.getParkingFee());
    }
}

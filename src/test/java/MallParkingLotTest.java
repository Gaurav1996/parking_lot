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
class MallParkingLotTest {

    private static ParkingLot parkingLot;

    @BeforeAll
    static void setUp() {
        parkingLot = new ParkingLot(100,80,10, FeeModel.MALL);
    }

    @Test
    void scooter_3H_30M_40RS() {

        Vehicle scooter1 = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime1 = LocalDateTime.of(2022, 5, 29, 14, 4, 7);

        ParkingTicket scooterTicket = parkingLot.park(scooter1, entryTime1);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, scooterTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime1, scooterTicket.getEntryDateTime());

        //Assert fee for 3 hours 30 mins
        LocalDateTime scooterExitTime = entryTime1.plusHours(3).plusMinutes(30);
        ParkingReceipt scooterReceipt = parkingLot.unpark(scooter1, scooterExitTime);
        Long expectedFees = 40L;
        assertEquals(entryTime1, scooterReceipt.getEntryTime());
        assertEquals(scooterExitTime, scooterReceipt.getExitTime());
        assertEquals(expectedFees, scooterReceipt.getParkingFee());
    }

    @Test
    void car_6H_1M_140RS() {
        Vehicle car = new Vehicle("C-01", VehicleType.CAR);
        LocalDateTime entryTime1 = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket carTicket = parkingLot.park(car, entryTime1);

        //Assert parking ticket
        int expectedSpotNumber = 101;
        assertEquals(expectedSpotNumber, carTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime1, carTicket.getEntryDateTime());

        //Assert fee for 6 hours 1 mins
        LocalDateTime carExitTime = entryTime1.plusHours(6).plusMinutes(1);
        ParkingReceipt carReceipt = parkingLot.unpark(car, carExitTime);
        Long expectedFees = 140L;
        assertEquals(entryTime1, carReceipt.getEntryTime());
        assertEquals(carExitTime, carReceipt.getExitTime());
        assertEquals(expectedFees, carReceipt.getParkingFee());
    }

    @Test
    void bus_1H_59M_100RS() {
        Vehicle bus = new Vehicle("B-01", VehicleType.BUS);
        LocalDateTime entryTime1 = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        ParkingTicket busTicket = parkingLot.park(bus, entryTime1);

        //Assert parking ticket
        int expectedSpotNumber = 181;
        assertEquals(expectedSpotNumber, busTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime1, busTicket.getEntryDateTime());
        //Assert fee for 1 hours 59 mins
        LocalDateTime busExitTime = entryTime1.plusHours(1).plusMinutes(59);
        ParkingReceipt busReceipt = parkingLot.unpark(bus, busExitTime);
        Long expectedFees = 100L;
        assertEquals(entryTime1, busReceipt.getEntryTime());
        assertEquals(busExitTime, busReceipt.getExitTime());
        assertEquals(expectedFees, busReceipt.getParkingFee());
    }
}

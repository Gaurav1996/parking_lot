import models.ParkingReceipt;
import models.ParkingTicket;
import models.Vehicle;
import models.VehicleType;
import models.feemodels.FeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Gaurav
 * 15/05/23
 */
class SmallMotorCycleParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(2,0,0, FeeModel.MALL);
    }

    @Test
    void assert_availableParking() {
        Vehicle scooter = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);

        ParkingTicket actualTicket = Assertions.assertDoesNotThrow(() -> parkingLot.park(scooter, entryTime));

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, actualTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, actualTicket.getEntryDateTime());
    }

    @Test
    void assert_parkingFullForScooter() {
        //1st scooter
        Vehicle vehicle1 = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime1 = LocalDateTime.of(2022, 5, 29, 14, 4, 7);

        ParkingTicket actualTicket = parkingLot.park(vehicle1, entryTime1);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, actualTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime1, actualTicket.getEntryDateTime());

        //2nd scooter
        Vehicle vehicle2 = new Vehicle("M-02", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime2 = LocalDateTime.of(2022, 5, 29, 14, 44, 7);

        actualTicket = parkingLot.park(vehicle2, entryTime2);

        //Assert parking ticket
        expectedSpotNumber = 2;
        assertEquals(expectedSpotNumber, actualTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime2, actualTicket.getEntryDateTime());

        //3rd scooter throws parking full error
        Vehicle vehicle3 = new Vehicle("M-03", VehicleType.MOTORCYCLE);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> parkingLot.park(vehicle3, entryTime2));
        assertTrue(exception.getMessage().contains("Parking full"));
    }

    @Test
    void assert_unAvailableParkingForCar() {
        Vehicle car = new Vehicle("C-01", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 4, 7);
        Assertions.assertThrows(RuntimeException.class, () -> parkingLot.park(car, entryTime));
    }

    @Test
    void scooter_56M_10RS() {

        Vehicle scooter = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 44, 7);

        ParkingTicket actualTicket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, actualTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, actualTicket.getEntryDateTime());

        LocalDateTime exitTime = entryTime.plusMinutes(56);
        ParkingReceipt actualReceipt = parkingLot.unpark(scooter, exitTime);
        //Assert receipt
        Long expectedFees = 10L;
        assertEquals(entryTime, actualReceipt.getEntryTime());
        assertEquals(exitTime, actualReceipt.getExitTime());
        assertEquals(expectedFees, actualReceipt.getParkingFee());
    }

    @Test
    void scooter_3H_30M_40RS() {
        Vehicle scooter = new Vehicle("M-01", VehicleType.MOTORCYCLE);
        LocalDateTime entryTime = LocalDateTime.of(2022, 5, 29, 14, 44, 7);

        ParkingTicket actualTicket = parkingLot.park(scooter, entryTime);

        //Assert parking ticket
        int expectedSpotNumber = 1;
        assertEquals(expectedSpotNumber, actualTicket.getParkingSpot().getSpotNumber());
        assertEquals(entryTime, actualTicket.getEntryDateTime());

        LocalDateTime exitTime = entryTime.plusHours(3).plusMinutes(30);
        ParkingReceipt actualReceipt = parkingLot.unpark(scooter, exitTime);
        //Assert receipt
        Long expectedFees = 40L;
        assertEquals(entryTime, actualReceipt.getEntryTime());
        assertEquals(exitTime, actualReceipt.getExitTime());
        assertEquals(expectedFees, actualReceipt.getParkingFee());
    }
}

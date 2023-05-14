import models.ParkingReceipt;
import models.ParkingSpot;
import models.ParkingTicket;
import models.Vehicle;
import models.feemodels.FeeModel;
import service.ParkingSpotService;
import service.ParkingTicketService;

import java.time.LocalDateTime;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingLot {

    private final ParkingSpotService parkingSpotService;
    private final ParkingTicketService parkingTicketService;

    public ParkingLot(int noOfMotorcycleSpots, int noOfCarSpots, int noOfBusSpots, FeeModel feeModel) {

        this.parkingSpotService = new ParkingSpotService(noOfMotorcycleSpots, noOfCarSpots, noOfBusSpots);
        this.parkingTicketService = new ParkingTicketService(feeModel);
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime) {

        ParkingSpot allottedParking = parkingSpotService.assignParking(vehicle);
        return parkingTicketService.issueTicket(allottedParking, vehicle, entryTime);
    }

    public ParkingReceipt unpark(Vehicle vehicle, LocalDateTime exitTime) {

        parkingSpotService.removeVehicle(vehicle);
        return parkingTicketService.generateReceipt(vehicle, exitTime);
    }
}

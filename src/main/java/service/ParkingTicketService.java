package service;

import dao.ParkingTicketDAO;
import models.*;
import models.feemodels.FeeModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingTicketService {

    private final FeeModel feeModel;
    private final ParkingTicketDAO ticketDAO;

    public ParkingTicketService(FeeModel feeModel) {
        this.feeModel = feeModel;
        this.ticketDAO = new ParkingTicketDAO();
    }

    public ParkingTicket issueTicket(ParkingSpot parkingSpot, Vehicle vehicle, LocalDateTime entryTime) {

        ParkingTicket parkingTicket = getTicket(vehicle)
                .orElseGet(() -> new ParkingTicket(parkingSpot, entryTime, vehicle));
        return ticketDAO.createParkingTicket(vehicle, parkingTicket);
    }

    public ParkingReceipt generateReceipt(Vehicle vehicle, LocalDateTime exitTime) {

        ParkingTicket parkingTicket = getTicket(vehicle)
                .orElseThrow(() -> new RuntimeException("Ticket not found for the vehicle " + vehicle));
        Duration duration = Duration.between(parkingTicket.getEntryDateTime(), exitTime);
        Long parkingFee = feeModel.getParkingFeeModel().calculateFees(vehicle.getVehicleType(), duration);
        return new ParkingReceipt(parkingTicket, exitTime, parkingFee);
    }

    private Optional<ParkingTicket> getTicket(Vehicle vehicle) {
        return Optional.ofNullable(ticketDAO.getParkingTicket(vehicle));
    }
}

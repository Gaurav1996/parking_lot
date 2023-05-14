package dao;

import models.ParkingTicket;
import models.Vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingTicketDAO {

    private final Map<Vehicle, ParkingTicket> tickets;

    public ParkingTicketDAO() {
        this.tickets = new HashMap<>();
    }

    public ParkingTicket createParkingTicket(Vehicle vehicle, ParkingTicket parkingTicket) {
        tickets.put(vehicle, parkingTicket);
        return parkingTicket;
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        return tickets.get(vehicle);
    }
}

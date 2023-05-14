package models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Gaurav
 * 14/05/23
 */
@Getter
public class ParkingTicket {
    private final String id;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryDateTime;
    private final Vehicle vehicle;

    public ParkingTicket(ParkingSpot parkingSpot, LocalDateTime entryDateTime, Vehicle vehicle) {
        this.id = UUID.randomUUID().toString();
        this.parkingSpot = parkingSpot;
        this.entryDateTime = entryDateTime;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ParkingTicket:" +
                "Ticket number:'" + id + '\'' +
                ", spotNumber:" + parkingSpot.getSpotNumber() +
                ", Entry Date-Time:" + entryDateTime.toString() +
                '}';
    }
}

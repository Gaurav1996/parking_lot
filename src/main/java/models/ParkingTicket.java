package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Gaurav
 * 14/05/23
 */
@Getter
@AllArgsConstructor
@ToString
public class ParkingTicket {
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryDateTime;
    private Vehicle vehicle;
}

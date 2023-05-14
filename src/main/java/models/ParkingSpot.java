package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Gaurav
 * 14/05/23
 */
@Getter
@ToString
public class ParkingSpot {

    private final int spotNumber;

    @Setter
    @ToString.Exclude
    private boolean isAvailable;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isAvailable = true;
    }
}

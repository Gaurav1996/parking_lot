package models;

import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Gaurav
 * 14/05/23
 */
@ToString
public class ParkingReceipt {

    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final Long parkingFee;

    public ParkingReceipt(ParkingTicket parkingTicket, LocalDateTime exitTime, Long parkingFee) {
        this.entryTime = parkingTicket.getEntryDateTime();
        this.exitTime = exitTime;
        this.parkingFee = parkingFee;
    }
}

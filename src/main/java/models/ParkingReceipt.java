package models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Gaurav
 * 14/05/23
 */
@Getter
public class ParkingReceipt {
    private final String id;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final Long parkingFee;

    public ParkingReceipt(ParkingTicket parkingTicket, LocalDateTime exitTime, Long parkingFee) {
        this.id = UUID.randomUUID().toString();
        this.entryTime = parkingTicket.getEntryDateTime();
        this.exitTime = exitTime;
        this.parkingFee = parkingFee;
    }

    @Override
    public String toString() {
        return "ParkingReceipt:" +
                "Receipt number: '" + id + '\'' +
                ", Entry Date-Time:" + entryTime +
                ", Exit Date-Time=" + exitTime.toString() +
                ", Fees:" + parkingFee +
                '}';
    }
}

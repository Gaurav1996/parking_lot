package dao;

import models.ParkingSpot;
import models.Vehicle;
import models.VehicleType;

import java.util.*;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingSpotDAO {

    private int spotNumber;
    private final Map<VehicleType, List<ParkingSpot>> parkingSpots;
    private final Map<Vehicle, ParkingSpot> assignedParkingSpot;

    public ParkingSpotDAO() {
        this.parkingSpots = new EnumMap<>(VehicleType.class);
        this.assignedParkingSpot = new HashMap<>();
        this.spotNumber = 1;
    }

    public void createParkingSpot(VehicleType vehicleType) {

        parkingSpots.computeIfAbsent(vehicleType, spot -> new ArrayList<>())
                .add(new ParkingSpot(spotNumber++));
    }

    public ParkingSpot assignParkingSpot(ParkingSpot parkingSpot, Vehicle vehicle) {

        parkingSpot.setAvailable(false);
        assignedParkingSpot.put(vehicle, parkingSpot);
        return parkingSpot;
    }

    public void unAssignParkingSpot(ParkingSpot parkingSpot, Vehicle vehicle) {

        parkingSpot.setAvailable(true);
        assignedParkingSpot.remove(vehicle);
    }

    public List<ParkingSpot> getParkingSpots(VehicleType vehicleType) {
        return parkingSpots.get(vehicleType);
    }

    public ParkingSpot getAllottedSpot(Vehicle vehicle) {
        return assignedParkingSpot.get(vehicle);
    }
}

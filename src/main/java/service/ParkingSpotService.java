package service;

import dao.ParkingSpotDAO;
import models.ParkingSpot;
import models.Vehicle;
import models.VehicleType;

import java.util.List;
import java.util.Optional;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingSpotService {

    private final ParkingSpotDAO parkingSpotDAO;

    public ParkingSpotService(int noOfMotorcycleSpots, int noOfCarSpots, int noOfBusSpots) {

        if(noOfMotorcycleSpots < 0 || noOfCarSpots < 0 || noOfBusSpots < 0) {
            throw new RuntimeException("Number of spots to be alloted cannot be negative");
        }
        this.parkingSpotDAO = new ParkingSpotDAO();
        for(int i = 0; i < noOfMotorcycleSpots; i++) {
            parkingSpotDAO.createParkingSpot(VehicleType.MOTORCYCLE);
        }
        for(int i = 0; i < noOfCarSpots; i++) {
            parkingSpotDAO.createParkingSpot(VehicleType.CAR);
        }
        for(int i = 0; i < noOfBusSpots; i++) {
            parkingSpotDAO.createParkingSpot(VehicleType.BUS);
        }
    }
    public ParkingSpot assignParking(Vehicle vehicle) {

        List<ParkingSpot> parkingSpots = parkingSpotDAO.getParkingSpots(vehicle.getVehicleType());

        ParkingSpot availableParkingSpot = parkingSpots
                .stream()
                .filter(ParkingSpot::isAvailable)
                .findFirst().orElseThrow(() -> new RuntimeException("Parking full for vehicle type " + vehicle.getVehicleType()));

        return parkingSpotDAO.assignParkingSpot(availableParkingSpot, vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {

        ParkingSpot allottedSpot = Optional.of(parkingSpotDAO.getAllottedSpot(vehicle))
                .orElseThrow(() -> new RuntimeException("Vehicle is not alloted any spot. Cannot unpark the vehicle " + vehicle));
        parkingSpotDAO.unAssignParkingSpot(allottedSpot, vehicle);
    }
}

package service;

import dao.ParkingSpotDAO;
import models.ParkingSpot;
import models.Vehicle;
import models.VehicleType;

import java.util.List;

/**
 * @author Gaurav
 * 14/05/23
 */
public class ParkingSpotService {

    private final ParkingSpotDAO parkingSpotDAO;

    public ParkingSpotService(int noOfMotorcycleSpots, int noOfCarSpots, int noOfBusSpots) {

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

        ParkingSpot allottedSpot = parkingSpotDAO.getAllottedSpot(vehicle);
        parkingSpotDAO.unAssignParkingSpot(allottedSpot, vehicle);
    }
}

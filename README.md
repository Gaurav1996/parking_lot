# Parking lot design

## Description
A parking lot is a dedicated area that is intended for parking vehicles. Parking lots are
present in every city and suburban area. Shopping malls, stadiums, airports, train stations,
and similar venues often feature a parking lot with a large capacity. A parking lot can spread
across multiple buildings with multiple floors or can be in a large open area.


● The parking lot will allow different types of vehicles to be parked:
- Motorcycles/Scooters 
- Cars/SUVs 
- Buses/Trucks

● Each vehicle will occupy a single spot and the spot size will be different for different
vehicles.

● The number of spots per vehicle type will be different for different parking lots. For
example 
- Motorcycles/scooters: 100 spots 
- Cars/SUVs: 80 spots 
- Buses/Trucks: 40 spots

● When a vehicle is parked, a parking ticket should be generated with the spot number
and the entry date-time.

● When a vehicle is unparked, a receipt should be generated with the entry date-time,
exit date-time, and the applicable fees to be paid.

### Mall
Per-hour flat fees 
- Motorcycle 10/hr 
- Car/SUV 20/hr 
- Bus/Truck 50/hr

### Stadium
Flat rate up to a few hours and then per-hour rate. The total fee is the sum of all the previous
interval fees. No parking spots for buses/trucks at the stadium.
- Motorcycle
  - [0, 4) hours - 30
  - [4, 12) hours - 60
  - [12, Infinity) hours - 100 per hour

- Car/SUV
  - [0, 4) hours - 60
  - [4, 12) hours - 120
  - [12, Infinity) hours - 200 per hour

### Airport
Flat rate up to one day. Then per-day rate. There is no summing up of the previous interval
fees. No parking spots for buses/trucks at the airport.
- Motorcycle
    - [0, 1) hours - Free
    - [1, 8) hours - 40
    - [8, 24) hours - 60
    - Each day - 80

- Car/SUV
    - [0, 12) hours - 60
    - [12, 24) hours - 80
    - Each day - 100

## Problem statement
Given a parking lot with details about the vehicle types that can be parked, the number of
spots, and the fee model for the parking lot; compute the fees to be paid for the parked
vehicles when the vehicle is unparked.

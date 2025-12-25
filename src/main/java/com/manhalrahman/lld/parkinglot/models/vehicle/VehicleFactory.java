package com.manhalrahman.lld.parkinglot.models.vehicle;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;

public class VehicleFactory {
    public static Vehicle create(String numberPlate, VehicleType vehicleType) {
        return switch (vehicleType) {
            case  CAR -> new Car(numberPlate);
            case  BIKE -> new Bike(numberPlate);
            case TRUCK -> new Truck(numberPlate);
        };
    }
}

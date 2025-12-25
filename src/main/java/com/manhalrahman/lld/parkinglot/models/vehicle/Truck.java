package com.manhalrahman.lld.parkinglot.models.vehicle;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;

public class Truck implements Vehicle {
    String numberPlate;
    VehicleType vehicleType;

    public Truck(String numberPlate) {
        this.numberPlate = numberPlate;
        this.vehicleType = VehicleType.TRUCK;
    }

    @Override
    public String getNumberPlate() {
        return this.numberPlate;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

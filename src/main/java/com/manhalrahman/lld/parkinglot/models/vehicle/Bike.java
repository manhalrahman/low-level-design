package com.manhalrahman.lld.parkinglot.models.vehicle;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;

public class Bike implements Vehicle{
    String numberPlate;
    VehicleType vehicleType;

    public Bike(String numberPlate) {
        this.numberPlate = numberPlate;
        this.vehicleType = VehicleType.BIKE;
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

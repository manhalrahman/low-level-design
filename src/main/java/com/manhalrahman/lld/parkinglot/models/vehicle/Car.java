package com.manhalrahman.lld.parkinglot.models.vehicle;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;

public class Car implements Vehicle {

    String numberPlate;
    VehicleType vehicleType;

    public Car(String numberPlate) {
        this.numberPlate = numberPlate;
        this.vehicleType = VehicleType.CAR;
    }

    @Override
    public String getNumberPlate() {
        return this.numberPlate;
    }

    @Override
    public VehicleType getVehicleType() {
        return null;
    }

}

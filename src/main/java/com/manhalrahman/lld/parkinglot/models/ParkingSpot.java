package com.manhalrahman.lld.parkinglot.models;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@RequiredArgsConstructor
public class ParkingSpot {
    String id;
    VehicleType vehicleType;

    AtomicBoolean isOccupied = new AtomicBoolean(false); // Thread safety for simultaneous spot booking.

    public ParkingSpot(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }

    public boolean tryOccupy() {
        return isOccupied.compareAndSet(false, true);
    }

    public void vacate() {
        isOccupied.set(false);
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }


}

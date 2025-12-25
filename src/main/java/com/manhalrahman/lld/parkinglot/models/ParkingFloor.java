package com.manhalrahman.lld.parkinglot.models;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ParkingFloor {
    private String floorId;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        parkingSpots = new HashMap<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getId(), parkingSpot);
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for(ParkingSpot parkingSpot : parkingSpots.values()) {
            if(parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.tryOccupy()) {
                return parkingSpot;
            }
        }
        return null;
    }
}

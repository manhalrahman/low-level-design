package com.manhalrahman.lld.parkinglot.models.gate;

import com.manhalrahman.lld.parkinglot.enums.GateType;
import com.manhalrahman.lld.parkinglot.models.Ticket;
import com.manhalrahman.lld.parkinglot.models.vehicle.Vehicle;
import com.manhalrahman.lld.parkinglot.service.ParkingLot;

import java.time.LocalDateTime;

public class EntryGate implements Gate{
    String gateNumber;

    public EntryGate(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        return ParkingLot.getInstance().parkVehicle(vehicle, entryTime);
    }

    @Override
    public GateType getType() {
        return GateType.ENTRY;
    }
}

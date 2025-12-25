package com.manhalrahman.lld.parkinglot.strategy.pricing;

import com.manhalrahman.lld.parkinglot.enums.VehicleType;
import com.manhalrahman.lld.parkinglot.models.Ticket;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime);
}

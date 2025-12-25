package com.manhalrahman.lld.parkinglot.models;

import com.manhalrahman.lld.parkinglot.enums.PaymentStatus;
import com.manhalrahman.lld.parkinglot.models.vehicle.Vehicle;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {
    String id;
    Vehicle vehicle;
    LocalDateTime entryTime;
    LocalDateTime exitTime;
    private String floorId;
    private String spotId;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
}

package com.manhalrahman.lld.parkinglot.service;

import com.manhalrahman.lld.parkinglot.enums.PaymentStatus;
import com.manhalrahman.lld.parkinglot.enums.PaymentType;
import com.manhalrahman.lld.parkinglot.enums.PricingStrategyType;
import com.manhalrahman.lld.parkinglot.models.ParkingFloor;
import com.manhalrahman.lld.parkinglot.models.ParkingSpot;
import com.manhalrahman.lld.parkinglot.models.Ticket;
import com.manhalrahman.lld.parkinglot.models.vehicle.Vehicle;
import com.manhalrahman.lld.parkinglot.strategy.payment.PaymentStrategy;
import com.manhalrahman.lld.parkinglot.strategy.payment.PaymentStrategyFactory;
import com.manhalrahman.lld.parkinglot.strategy.pricing.PricingStrategy;
import com.manhalrahman.lld.parkinglot.strategy.pricing.PricingStrategyFactory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> tickets = new HashMap<>();

    @Setter
    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.EVENT_BASED);
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        floors.put(floor.getFloorId(), floor);
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        for(ParkingFloor floor : floors.values()) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getVehicleType());
            if(spot != null) {
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = Ticket.builder()
                        .id(ticketId)
                        .entryTime(entryTime)
                        .floorId(floor.getFloorId())
                        .paymentStatus(PaymentStatus.PENDING)
                        .build();
                tickets.put(ticketId, ticket);
                return ticket;
            }
        }
        return null;
    }


    public void unpark(String ticketId, LocalDateTime exitTime, PaymentType paymentType) {
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.get(paymentType);
        Ticket ticket = tickets.get(ticketId);
        if(ticket != null) {
            double payableAmount = pricingStrategy.calculateFee(ticket.getVehicle().getVehicleType(), ticket.getEntryTime(), exitTime);
            boolean paymentSuccess = paymentStrategy.pay(ticket, payableAmount);
            if(paymentSuccess) {
                ticket.setExitTime(exitTime);
                ParkingSpot parkingSpot = floors.get(ticket.getFloorId()).getParkingSpots().get(ticket.getSpotId());
                parkingSpot.vacate();
                tickets.remove(ticketId);
                System.out.println("Vehicle has exited successfully");
            } else {
                System.out.println("Payment unsuccessful for ticketId " +  ticketId);
            }
        }
    }

}

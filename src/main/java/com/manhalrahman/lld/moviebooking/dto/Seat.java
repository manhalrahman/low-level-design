package com.manhalrahman.lld.moviebooking.dto;

import com.manhalrahman.lld.moviebooking.strategy.PricingStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {
    String seatNumber;
    PricingStrategy strategy;
}

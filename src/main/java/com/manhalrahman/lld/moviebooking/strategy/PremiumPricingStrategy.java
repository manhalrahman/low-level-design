package com.manhalrahman.lld.moviebooking.strategy;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class PremiumPricingStrategy implements PricingStrategy {
    private final BigDecimal price;
    @Override
    public BigDecimal getPrice() {
        return price;
    }
}

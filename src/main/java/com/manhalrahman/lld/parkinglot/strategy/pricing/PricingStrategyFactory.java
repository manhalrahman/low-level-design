package com.manhalrahman.lld.parkinglot.strategy.pricing;

import com.manhalrahman.lld.parkinglot.enums.PricingStrategyType;

public class PricingStrategyFactory {
    public static PricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED ->  new EventBasedPricing();
        };
    }
}

package com.manhalrahman.lld.parkinglot.strategy.payment;

import com.manhalrahman.lld.parkinglot.enums.PaymentType;

public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentType paymentType) {
        return switch (paymentType) {
            case PaymentType.UPI -> new UpiPayment();
            case PaymentType.CASH ->  new CashPayment();
            case PaymentType.CARD -> new CardPayment();
        };
    }
}

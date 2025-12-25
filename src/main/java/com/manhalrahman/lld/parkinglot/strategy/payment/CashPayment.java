package com.manhalrahman.lld.parkinglot.strategy.payment;


import com.manhalrahman.lld.parkinglot.enums.PaymentType;
import com.manhalrahman.lld.parkinglot.models.Ticket;

import java.math.BigDecimal;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(Ticket ticket, double amount) {
        System.out.println("Payment done for ticket " + ticket.getId() + " with amount " + amount + " via " + PaymentType.CASH.name());
        return true;
    }
}

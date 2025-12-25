package com.manhalrahman.lld.parkinglot.strategy.payment;


import com.manhalrahman.lld.parkinglot.models.Ticket;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean pay(Ticket ticket, double amount);
}

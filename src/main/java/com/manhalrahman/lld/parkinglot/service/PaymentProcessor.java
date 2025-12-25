package com.manhalrahman.lld.parkinglot.service;

import com.manhalrahman.lld.parkinglot.enums.PaymentStatus;
import com.manhalrahman.lld.parkinglot.models.Ticket;
import com.manhalrahman.lld.parkinglot.strategy.payment.PaymentStrategy;

public class PaymentProcessor {
    private final PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(Ticket ticket, double amount) {
        boolean success = paymentStrategy.pay(ticket, amount);
        if(success) {
            ticket.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            ticket.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println("Payment failed for ticket: " + ticket.getId());
        }
        return success;
    }
}

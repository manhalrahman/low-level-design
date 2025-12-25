package com.manhalrahman.lld.amazonlocker.entities;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Account {
    String accountId;
    String name;
    AccountPolicy accountPolicy;
    BigDecimal usageCharges;

    public Account(String accountId, String name, AccountPolicy accountPolicy) {
        this.accountId = accountId;
        this.name = name;
        this.accountPolicy = accountPolicy;
        this.usageCharges = BigDecimal.ZERO;
    }

    public void addUsageCharges(BigDecimal amount) {
        this.usageCharges = this.usageCharges.add(amount);
    }
}

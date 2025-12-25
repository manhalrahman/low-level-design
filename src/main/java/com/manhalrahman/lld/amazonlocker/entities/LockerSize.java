package com.manhalrahman.lld.amazonlocker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum LockerSize {

    SMALL(
            "Small",
            new BigDecimal("5.00"),
            new BigDecimal("10.00"),
            new BigDecimal("10.00"),
            new BigDecimal("10.00")),
    MEDIUM(
            "Medium",
            new BigDecimal("10.00"),
            new BigDecimal("20.00"),
            new BigDecimal("20.00"),
            new BigDecimal("20.00")),

    LARGE("Large",
            new BigDecimal("15.00"),
            new BigDecimal("30.00"),
            new BigDecimal("30.00"),
            new BigDecimal("30.00"));

    private String sizeName;
    final BigDecimal dailyCharge;
    final BigDecimal width;
    final BigDecimal height;
    final BigDecimal depth;

}

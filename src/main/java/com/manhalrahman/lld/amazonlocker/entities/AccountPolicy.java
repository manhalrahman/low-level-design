package com.manhalrahman.lld.amazonlocker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountPolicy {
    Integer freePeriodInDays;
    Integer maxWaitDays;
}

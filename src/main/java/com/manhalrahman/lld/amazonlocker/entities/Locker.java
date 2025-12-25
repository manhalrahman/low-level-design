package com.manhalrahman.lld.amazonlocker.entities;

import com.manhalrahman.lld.amazonlocker.entities.shippingpackage.ShippingPackage;
import com.manhalrahman.lld.amazonlocker.exceptions.MaximumStoragePeriodExceededException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Locker {
    LockerSize size;
    String accessCode;
    Date assignmentDate;
    ShippingPackage shippingPackage;

    public boolean isAvailable() {
        return shippingPackage == null;
    }

    public void assignShippingPackage(ShippingPackage shippingPackage, Date date) {
        this.shippingPackage = shippingPackage;
        this.assignmentDate = date;
        this.accessCode = generateAccessCode();
    }

    private String generateAccessCode() {
        //  generates a random 5 character string containing numbers or capital letters
        String randomAccessCode = UUID.randomUUID().toString();
        randomAccessCode = randomAccessCode.replaceAll("-", "").toUpperCase();
        return randomAccessCode.substring(0, 3) + "-" + randomAccessCode.charAt(3) + "-" + randomAccessCode.length();
    }

    public boolean isAccessCodeValid(String accessCode) {
        if(accessCode == null || accessCode.isEmpty()) {
            return false;
        }
        return accessCode.equals(this.accessCode);
    }

    public void releaseLocker() {
        this.accessCode = null;
        this.assignmentDate = null;
        this.shippingPackage = null;
    }

    public BigDecimal calculateCost() {
        if(assignmentDate == null || shippingPackage == null) {
            return BigDecimal.ZERO;
        }

        AccountPolicy accountPolicy = this.shippingPackage.getUser().getAccountPolicy();

        long totalDaysUsed = (new Date().getTime() - this.assignmentDate.getTime()) / (24 * 60 * 60 * 1000);
        if(totalDaysUsed > accountPolicy.getMaxWaitDays()) {
            shippingPackage.updateShippingStatus(ShippingStatus.EXPIRED);
            throw new MaximumStoragePeriodExceededException("Maximum storage period of " + accountPolicy.getMaxWaitDays() + " days exceeded");
        }

        long chargeableDays = Math.max(0, totalDaysUsed - accountPolicy.getMaxWaitDays());
        return size.dailyCharge.multiply(BigDecimal.valueOf(chargeableDays));
    }
}


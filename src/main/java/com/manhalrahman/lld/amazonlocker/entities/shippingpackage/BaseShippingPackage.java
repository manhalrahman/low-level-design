package com.manhalrahman.lld.amazonlocker.entities.shippingpackage;

import com.manhalrahman.lld.amazonlocker.entities.Account;
import com.manhalrahman.lld.amazonlocker.entities.LockerSize;
import com.manhalrahman.lld.amazonlocker.entities.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BaseShippingPackage implements ShippingPackage {
    BigDecimal height;
    BigDecimal width;
    BigDecimal depth;
    Account account;
    LockerSize lockerSize;
    ShippingStatus shippingStatus;

    @Override
    public String getOrderId() {
        return "";
    }

    @Override
    public Account getUser() {
        return null;
    }

    @Override
    public LockerSize getLockerSize() {
        return null;
    }

    @Override
    public BigDecimal getHeight() {
        return null;
    }

    @Override
    public BigDecimal getWidth() {
        return null;
    }

    @Override
    public BigDecimal getDepth() {
        return null;
    }

    @Override
    public void updateShippingStatus(ShippingStatus shippingStatus) {

    }
}

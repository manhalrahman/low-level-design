package com.manhalrahman.lld.amazonlocker.entities.shippingpackage;

import com.manhalrahman.lld.amazonlocker.entities.Account;
import com.manhalrahman.lld.amazonlocker.entities.LockerSize;
import com.manhalrahman.lld.amazonlocker.entities.ShippingStatus;

import java.math.BigDecimal;

public interface ShippingPackage {
    String getOrderId();
    Account getUser();
    LockerSize getLockerSize();
    BigDecimal getHeight();
    BigDecimal getWidth();
    BigDecimal getDepth();
    void updateShippingStatus(ShippingStatus shippingStatus);
}

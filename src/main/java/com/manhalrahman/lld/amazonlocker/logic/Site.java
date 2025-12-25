package com.manhalrahman.lld.amazonlocker.logic;

import com.manhalrahman.lld.amazonlocker.entities.Locker;
import com.manhalrahman.lld.amazonlocker.entities.LockerSize;
import com.manhalrahman.lld.amazonlocker.entities.ShippingStatus;
import com.manhalrahman.lld.amazonlocker.entities.shippingpackage.ShippingPackage;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Site {
    Map<LockerSize, Set<Locker>> lockers;

    public Locker findAvailableLocker(LockerSize size) {
        Set<Locker> lockers = this.lockers.get(size);
        for(Locker locker : lockers) {
            if(locker.isAvailable()) {
                return locker;
            }
        }
        return null;
    }

    public Locker placePackage(ShippingPackage shippingPackage, Date date) {
        Locker locker = findAvailableLocker(shippingPackage.getLockerSize());
        if(locker != null) {
            locker.assignShippingPackage(shippingPackage, date);
            shippingPackage.updateShippingStatus(ShippingStatus.IN_LOCKER);
        }
        return locker;
    }

}

package com.manhalrahman.lld.amazonlocker.logic;

import com.manhalrahman.lld.amazonlocker.entities.Account;
import com.manhalrahman.lld.amazonlocker.entities.Locker;
import com.manhalrahman.lld.amazonlocker.entities.ShippingStatus;
import com.manhalrahman.lld.amazonlocker.entities.notification.NotificationStrategy;
import com.manhalrahman.lld.amazonlocker.entities.shippingpackage.ShippingPackage;
import com.manhalrahman.lld.amazonlocker.exceptions.MaximumStoragePeriodExceededException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LockerManager {
    private Site site;
    private Map<String, Account> accounts;
    private Map<String, Locker> accessCodeMap = new HashMap<>(); // caching for fast lookup
    private NotificationStrategy notificationService;

    public LockerManager(Site site, Map<String, Account> accounts, NotificationStrategy notificationService) {
        this.site = site;
        this.accounts = accounts;
        this.notificationService = notificationService;
    }

    public Locker placePackage(ShippingPackage shippingPackage, Date date) {
        Locker locker = site.placePackage(shippingPackage, date);
        if(locker != null) {

            accessCodeMap.put(locker.getAccessCode(), locker);
            Account userAccount = shippingPackage.getUser();
            accounts.put(userAccount.getAccountId(), userAccount);

            notificationService.sendNotification("Package assigned to locker " + locker.getAccessCode(), userAccount);
        }
        return locker;
    }

    public Locker pickUpPackage(String accessCode) {
        if(accessCodeMap.containsKey(accessCode)) {
            Locker locker = accessCodeMap.get(accessCode);
            if(locker.isAccessCodeValid(accessCode)) {
                try {
                    BigDecimal charge = locker.calculateCost();
                    ShippingPackage shippingPackage = locker.getShippingPackage();
                    locker.releaseLocker();
                    shippingPackage.getUser().addUsageCharges(charge);
                    shippingPackage.updateShippingStatus(ShippingStatus.PICKED_UP);
                    return locker;
                } catch (MaximumStoragePeriodExceededException e) {
                    locker.releaseLocker();
                    return locker;
                }
            }
        }

        return null;
    }


}

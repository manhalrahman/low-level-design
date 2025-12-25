package com.manhalrahman.lld.amazonlocker.entities.notification;

import com.manhalrahman.lld.amazonlocker.entities.Account;

public interface NotificationStrategy {
    void sendNotification(String message, Account account);
}

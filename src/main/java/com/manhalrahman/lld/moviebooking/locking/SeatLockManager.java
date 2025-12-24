package com.manhalrahman.lld.moviebooking.locking;

import com.manhalrahman.lld.moviebooking.dto.Screening;
import com.manhalrahman.lld.moviebooking.dto.Seat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockManager {
    private final Map<String, SeatLock> lockedSeats = new ConcurrentHashMap<>();
    private final Duration lockDuration;

    public SeatLockManager(Duration duration) {
        this.lockDuration = duration;
    }

    public synchronized boolean lockSeat(Screening screening, Seat seat, String userId) {
        String lockKey = generateLockKey(screening, seat);

        cleanUpLockIfExpired(lockKey);

        if(isLocked(screening, seat)) {
            return false;
        }

        SeatLock seatLock = new SeatLock(userId, LocalDateTime.now().plus(lockDuration));
        lockedSeats.put(lockKey, seatLock);
        return true;
    }

    public synchronized boolean isLocked(Screening screening, Seat seat) {
        String lockKey = generateLockKey(screening, seat);
        cleanUpLockIfExpired(lockKey);
        return lockedSeats.containsKey(lockKey);
    }

    private void cleanUpLockIfExpired(String lockKey) {
        SeatLock lock = lockedSeats.get(lockKey);
        if(lock != null && lock.isExpired()) {
            lockedSeats.remove(lockKey);
        }
    }

    public String generateLockKey(Screening screening, Seat seat) {
        return screening.getId() + "-" + seat.getSeatNumber();
    }


    public static class SeatLock {
        private final String userId;
        private final LocalDateTime expirationTime;

        public SeatLock(String userId, LocalDateTime expirationTime) {
            this.userId = userId;
            this.expirationTime = expirationTime;
        }

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expirationTime);
        }

        public String getUserId() {
            return userId;
        }
        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }
    }
}

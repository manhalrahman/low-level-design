package com.manhalrahman.lld.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Screening {
    Movie movie;
    Room room;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}

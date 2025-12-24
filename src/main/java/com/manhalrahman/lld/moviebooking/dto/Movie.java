package com.manhalrahman.lld.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Movie {
    String movieName;
    String genre;
    String pgRating;
    int durationInMinutes;
}

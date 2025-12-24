package com.manhalrahman.lld.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Ticket {
    Screening screening;
    Seat seat;
    BigDecimal price;
}

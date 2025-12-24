package com.manhalrahman.lld.moviebooking.logic;


import com.manhalrahman.lld.moviebooking.dto.Movie;
import com.manhalrahman.lld.moviebooking.dto.Screening;
import com.manhalrahman.lld.moviebooking.dto.Seat;
import com.manhalrahman.lld.moviebooking.dto.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreeningManager {
    Map<Movie, List<Screening>>  screeningsByMovie;
    Map<Screening, List<Ticket>> ticketsByScreening;

    public ScreeningManager() {
        this.screeningsByMovie = new HashMap<>();
        this.ticketsByScreening = new HashMap<>();
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningsByMovie.computeIfAbsent(movie, k -> new ArrayList<>()).add(screening);
    }

    public void addTicket(Screening screening, Ticket ticket) {
        ticketsByScreening.computeIfAbsent(screening, k -> new ArrayList<>()).add(ticket);
    }

    public List<Screening> getScreeningForMovie(Movie movie) {
        return screeningsByMovie.getOrDefault(movie, new ArrayList<>());
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return ticketsByScreening.getOrDefault(screening, new ArrayList<>());
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        List<Seat> allSeats = screening.getRoom().getLayout().getAllSeats();
        List<Ticket> tickets = getTicketsForScreening(screening);
        List<Seat> availableSeats = new ArrayList<>(allSeats);
        for(Ticket ticket : tickets) {
            availableSeats.remove(ticket.getSeat());
        }
        return availableSeats;
    }

}

package com.manhalrahman.lld.moviebooking.logic;

import com.manhalrahman.lld.moviebooking.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MovieBookingSystem {
    List<Movie> movies;
    List<Cinema> cinemas;
    ScreeningManager screeningManager;

    public MovieBookingSystem() {
        movies = new ArrayList<>();
        cinemas = new ArrayList<>();
        screeningManager = new ScreeningManager();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void bookTicket(Screening screening, Seat seat) {
        BigDecimal pricing = seat.getStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, pricing);
        screeningManager.addTicket(screening, ticket);
    }

    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningManager.getScreeningForMovie(movie);
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        return screeningManager.getAvailableSeats(screening);
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return screeningManager.getTicketsForScreening(screening);
    }

    public int getTicketCount(Screening screening) {
        return getTicketsForScreening(screening).size();
    }

}

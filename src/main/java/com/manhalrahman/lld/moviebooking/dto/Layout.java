package com.manhalrahman.lld.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Layout {
    int rows;
    int columns;
    Map<Integer, Map<Integer, Seat>> seatsByPosition;
    Map<String, Seat> seatsByNumber;

    public Layout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seatsByPosition = new HashMap<>();
        this.seatsByNumber = new HashMap<>();
        initializeLayout();
    }

    private void initializeLayout() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                String seatNumber = i + "-" + j;
                addSeat(seatNumber, new Seat(seatNumber, null), i, j);
            }
        }
    }

    public void addSeat(String seatNumber, Seat seat, int row, int column) {
        seatsByNumber.put(seatNumber, seat);
        seatsByPosition.computeIfAbsent(row, k -> new HashMap<>()).put(column, seat);
    }

    public Seat getSeat(int row, int column) {
        return seatsByPosition.get(row).get(column);
    }

    public Seat getSeatByPosition(int row, int column) {
        return seatsByPosition.get(row).get(column);
    }

    public List<Seat> getAllSeats() {
        return List.copyOf(seatsByNumber.values());
    }

}

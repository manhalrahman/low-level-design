package com.manhalrahman.lld.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Cinema {
    String name;
    String location;
    List<Room> rooms;

    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }
}

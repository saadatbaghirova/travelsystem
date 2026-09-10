package com.example.travelsystem.service;

import com.example.travelsystem.model.Hotel;
import com.example.travelsystem.model.Room;

import java.util.List;

public interface RoomService {

    Room saveRoom(Room room);

    List<Room> getAllRooms();

    Room findRoomById(Long id);

    Room updateRoom(Room room);

    List<Room> findRoomsByPrice(Double minPrice, Double maxPrice);

    void deleteRoomById(Long id);
}
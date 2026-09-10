package com.example.travelsystem.repository;

import com.example.travelsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByPriceBetween(Double minPrice, Double maxPrice);

}
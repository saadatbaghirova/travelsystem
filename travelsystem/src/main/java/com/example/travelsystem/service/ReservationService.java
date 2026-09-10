package com.example.travelsystem.service;

import com.example.travelsystem.model.Hotel;
import com.example.travelsystem.model.Reservation;
import com.example.travelsystem.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation);

    Reservation findReservationById(Long id) ;

    List<Reservation>  getAllReservations();

    Reservation updateReservation(Reservation reservation);

    void deleteReservationById(Long id) ;
}
package com.packtpub.service;

import com.packtpub.model.Reservation;

import java.util.List;

public interface ReservationService {
    
    List<Reservation> findCustomerReservations(String customerId);

    List<Reservation> findReservations();

    Reservation findReservation(String reservationId);

    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Reservation reservation);
}
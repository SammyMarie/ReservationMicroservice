package com.packtpub.service.impl;

import com.packtpub.model.Reservation;
import com.packtpub.repository.ReservationRepository;
import com.packtpub.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> findReservations() {
        return repository.findAll();
    }

    @Override
    public Reservation findReservation(String reservationId) {
        return repository.findOne(reservationId);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public List<Reservation> findCustomerReservations(String customerId) {
        return repository.findReservationByCustomerId(customerId);
    }
}
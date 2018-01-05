package com.packtpub.api;

import com.packtpub.model.Reservation;
import com.packtpub.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RefreshScope
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservations/{reservationId}")
    public ResponseEntity<Reservation> findReservation(@PathVariable("reservationId") String reservationId){
        log.info(String.format("Finding a Reservation with reservationId, %s", reservationId));
        Reservation reservation = reservationService.findReservation(reservationId);

        if(reservation != null){
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(reservation);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/reservations")
    public ResponseEntity<List<Reservation>> findReservations(){
        log.info("Finding Reservations");
        List<Reservation> reservations = reservationService.findReservations();

        if(CollectionUtils.isNotEmpty(reservations)){
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/reservations/customers/{customerId}")
    public ResponseEntity<List<Reservation>> findCustomerReservations(@PathVariable("customerId") String customerId){
        log.info(String.format("Finding Reservation using customerId, %s", customerId));
        List<Reservation> reservations = reservationService.findCustomerReservations(customerId);

        if(CollectionUtils.isNotEmpty(reservations)){
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody @Valid Reservation reservation){
        log.info(String.format("Creating a Reservation, %s", reservation));

        reservationService.createReservation(reservation);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(value = "/reservations/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable String reservationId, @RequestBody @Valid Reservation reservation){
        log.info(String.format("Updating a Reservation, %s", reservation));

        Reservation reservationSearch = reservationService.findReservation(reservationId);

        if(reservationSearch != null && reservationId.equals(reservation.getReservationId())){

            reservationService.updateReservation(reservation);
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.status(409)
                                 .body("Request reservationId and returned Id don't match!");
        }
    }
}
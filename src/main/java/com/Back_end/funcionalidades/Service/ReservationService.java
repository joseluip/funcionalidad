/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.ReservationRepository;
import com.Back_end.funcionalidades.Entities.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int idReservation) {
        return reservationRepository.getReservation(idReservation);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> j = reservationRepository.getReservation(reservation.getIdReservation());
            if (j.isPresent()) {
                return reservation;
            } else {
                return reservationRepository.save(reservation);
            }
        }
    }

    public Reservation update(Reservation k) {
        if (k.getIdReservation() != null) {
            Optional<Reservation> tu = reservationRepository.getReservation(k.getIdReservation());
            if (!tu.isPresent()) {
                reservationRepository.save(tu.get());
            }
        }

        return k;
    }

    public boolean delete(int idRservation) {
        boolean bandera = false;
        Optional<Reservation> t = reservationRepository.getReservation(idRservation);
        if (t.isPresent()) {
            reservationRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}

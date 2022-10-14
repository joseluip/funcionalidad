/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.DTOs.CompletedAndCancelled;
import com.Back_end.funcionalidades.DTOs.TotalAndClient;
import com.Back_end.funcionalidades.Repository.ReservationRepository;
import com.Back_end.funcionalidades.Entities.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if (tu.isPresent()) {
                if (k.getStartDate() != null) {
                    tu.get().setStartDate(k.getStartDate());
                }
                if (k.getDevolutionDate() != null) {
                    tu.get().setDevolutionDate(k.getDevolutionDate());
                }
                if (k.getStatus() != null) {
                    tu.get().setStatus(k.getStatus());
                }
                reservationRepository.save(tu.get());
                return tu.get();
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

    public List<Reservation> getReservationsBetweenDatesReport(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date a = new Date();
        Date b = new Date();
        try {
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        if (a.before(b)) {
            return reservationRepository.getReservationsBetweenDates(a, b);
        } else {
            return new ArrayList<>();
        }
    }

    public CompletedAndCancelled getReservationStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");       
        return new CompletedAndCancelled(completed.size(), cancelled.size());
    }

    public List<TotalAndClient> getTopClientsReport() {
        return reservationRepository.getTopClients();
    }

}

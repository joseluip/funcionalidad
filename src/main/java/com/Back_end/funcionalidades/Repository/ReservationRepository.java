/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Repository;

import com.Back_end.funcionalidades.CrudRepository.ReservationCrudRepository;
import com.Back_end.funcionalidades.DTOs.TotalAndClient;
import com.Back_end.funcionalidades.Entities.Client;
import com.Back_end.funcionalidades.Entities.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jljos
 */
@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int idReservation) {
        return reservationCrudRepository.findById(idReservation);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationsBetweenDates(Date a, Date b) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<Reservation> getReservationByStatus(String status) {
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<TotalAndClient> getTopClients() {
        List<TotalAndClient> resultado = new ArrayList<>();
        List<Object[]> reporte = reservationCrudRepository.getTotalReservationByClient();
        for (int i = 0; i < reporte.size(); i++) {
            resultado.add(new TotalAndClient((Long) reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return resultado;
    }

}

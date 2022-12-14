/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Controller;

import com.Back_end.funcionalidades.DTOs.CompletedAndCancelled;
import com.Back_end.funcionalidades.DTOs.TotalAndClient;
import com.Back_end.funcionalidades.Service.ReservationService;
import com.Back_end.funcionalidades.Entities.Reservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jljos
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation h) {
        return reservationService.save(h);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int idReservation) {
        return reservationService.delete(idReservation);
    }
    
    @GetMapping("/report-dates/{fechaI}/{fechaF}")
    public List<Reservation> getReservationsBetweenDatesReport(@PathVariable("fechaI")String fechaI, @PathVariable("fechaF") String fechaF){
        return reservationService.getReservationsBetweenDatesReport(fechaI,fechaF);
    }
    
    @GetMapping("/report-status")
    public CompletedAndCancelled getReservationStatusReport(){
        return reservationService.getReservationStatusReport();
    }
    
    @GetMapping("/report-clients")
    public List<TotalAndClient> getTopClientsReport(){
        return reservationService.getTopClientsReport();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Back_end.funcionalidades.CrudRepository;

import com.Back_end.funcionalidades.Entities.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jljos
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {

    //1 reporte
    /* seleciionamos todos los datos de la clase Reservation mientras startDate es despues fechaI y deovlutionDate es antes fechaF 
    SELECT * FROM Reservation WHERE starDate AFTER fechaI AND devolutionDate BEFORE fechaF;
     */
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date fechaI, Date fechaF);
    // 2 reporte
    /*
    seleccionamos todos los datos de la calse reservation donde status es igual al valor que necesito
    SELECT * FROM Reservation WHERE status = "valor necesario";
    */

    public List<Reservation> findAllByStatus(String status);
    
    /*
    selleccionamos la clase client,contamos todos los datos de client desde reservation por grupos en orden desendente
    SELECT client, Count(client) FROM Reservation GROUP BY client ORDER BY COUNT(client) DESC;
    */
    @Query("SELECT j.client, COUNT(j.client) FROM Reservation AS j GROUP BY j.client ORDER BY COUNT(j.client) DESC")
    public List<Object[]> getTotalReservationByClient();

}

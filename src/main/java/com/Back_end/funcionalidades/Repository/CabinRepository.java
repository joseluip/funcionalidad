/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Repository;

import com.Back_end.funcionalidades.CrudRepository.CabinCrudRepository;
import com.Back_end.funcionalidades.Entities.Cabin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jljos
 */
@Repository
public class CabinRepository {

    @Autowired
    private CabinCrudRepository cabinCrudRepository;

    public List<Cabin> getAll() {
        return (List<Cabin>) cabinCrudRepository.findAll();
    }

    public Optional<Cabin> getCabin(int id) {
        return cabinCrudRepository.findById(id);
    }

    public Cabin save(Cabin cabin) {
        return cabinCrudRepository.save(cabin);
    }

    public void delete(Cabin cabin) {
        cabinCrudRepository.delete(cabin);
    }
}

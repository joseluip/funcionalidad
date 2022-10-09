/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Repository;

import com.Back_end.funcionalidades.CrudRepository.AdminCrudRepository;
import com.Back_end.funcionalidades.Entities.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jljos
 */
@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAll() {
        return (List<Admin>) adminCrudRepository.findAll();
    }

    public Optional<Admin> getAdmin(int idAdmin) {
        return adminCrudRepository.findById(idAdmin);
    }

    public Admin save(Admin admin) {
        return adminCrudRepository.save(admin);
    }

    public void delete(Admin admin) {
        adminCrudRepository.delete(admin);
    }
    
}

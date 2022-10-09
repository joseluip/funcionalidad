/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.AdminRepository;
import com.Back_end.funcionalidades.Entities.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int idAdmin) {
        return adminRepository.getAdmin(idAdmin);
    }

    public Admin save(Admin admin) {
        if (admin.getIdAdmin() == null) {
            return adminRepository.save(admin);
        } else {
            Optional<Admin> j = adminRepository.getAdmin(admin.getIdAdmin());
            if (!j.isPresent()) {
                return admin;
            } else {
                return adminRepository.save(admin);
            }
        }
    }

    public Admin update(Admin k) {
        if (k.getIdAdmin() != null) {
            Optional<Admin> tu = adminRepository.getAdmin(k.getIdAdmin());
            if (!tu.isPresent()) {
                if (k.getName() != null) {
                    tu.get().setName(k.getName());
                }
                if (k.getEmail() != null) {
                    tu.get().setEmail(k.getEmail());
                }
                if (k.getPassword() != null) {
                    tu.get().setPassword(k.getPassword());
                }
                adminRepository.save(tu.get());
                return tu.get();
            } else {
                return k;
            }
        } else {
            return k;
        }
    }

    public boolean delete(int idAdmin) {
        boolean bandera = false;
        Optional<Admin> t = adminRepository.getAdmin(idAdmin);
        if (!t.isPresent()) {
            adminRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}

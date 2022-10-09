/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Controller;

import com.Back_end.funcionalidades.Service.CabinService;
import com.Back_end.funcionalidades.Entities.Cabin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jljos
 */
@RestController
@RequestMapping("/api/Cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;

    @GetMapping("/all")
    public List<Cabin> getAll() {
        return cabinService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cabin save(@RequestBody Cabin h) {
        return cabinService.save(h);
    }

}

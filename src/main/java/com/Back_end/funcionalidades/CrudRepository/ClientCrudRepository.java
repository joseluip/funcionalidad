/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Back_end.funcionalidades.CrudRepository;

import com.Back_end.funcionalidades.Entities.Client;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jljos
 */
public interface ClientCrudRepository extends CrudRepository<Client,Integer> {
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Repository;

import com.Back_end.funcionalidades.CrudRepository.ClientCrudRepository;
import com.Back_end.funcionalidades.Entities.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jljos
 */
@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository clientCrudRepository;
    
     public List<Client> getAll() {
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Optional<Client> getClient(int idClient) {
        return clientCrudRepository.findById(idClient);
    }

    public Client save(Client client) {
        return clientCrudRepository.save(client);
    }

    public void delete(Client client) {
        clientCrudRepository.delete(client);
    }

}

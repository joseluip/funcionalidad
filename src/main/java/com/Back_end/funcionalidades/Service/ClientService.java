/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.ClientRepository;
import com.Back_end.funcionalidades.Entities.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getCabin(int idClient) {
        return clientRepository.getClient(idClient);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> j = clientRepository.getClient(client.getIdClient());
            if (j.isEmpty()) {
                return client;
            } else {
                return clientRepository.save(client);
            }
        }
    }

    public Client update(Client k) {
        if (k.getIdClient() != null) {
            Optional<Client> tu = clientRepository.getClient(k.getIdClient());
            if (tu.isEmpty()) {
                if (k.getName() != null) {
                    tu.get().setName(k.getName());
                }
                if (k.getEmail() != null) {
                    tu.get().setEmail(k.getEmail());
                }
                if (k.getAge() != null) {
                    tu.get().setAge(k.getAge());
                }
                if (k.getPassword() != null) {
                    tu.get().setPassword(k.getPassword());
                }
                clientRepository.save(tu.get());
                return tu.get();
            } else {
                return k;
            }
        } else {
            return k;
        }
    }

    public boolean delete(int idClient) {
        boolean bandera = false;
        Optional<Client> t = clientRepository.getClient(idClient);
        if (t.isEmpty()) {
            clientRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }

}

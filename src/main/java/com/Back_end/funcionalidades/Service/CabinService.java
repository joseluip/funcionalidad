/*
 * Creacion De los servicios del CRUD
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.CabinRepository;
import com.Back_end.funcionalidades.Entities.Cabin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class CabinService {

    @Autowired
    private CabinRepository cabinRepository;

    public List<Cabin> getAll() {
        return cabinRepository.getAll();
    }

    public Optional<Cabin> getCabin(int id) {
        return cabinRepository.getCabin(id);
    }

    public Cabin save(Cabin cabin) {
        if (cabin.getId() == null) {
            return cabinRepository.save(cabin);
        } else {
            Optional<Cabin> j = cabinRepository.getCabin(cabin.getId());
            if (!j.isPresent()) {
                return cabin;
            } else {
                return cabinRepository.save(cabin);
            }
        }
    }

    public Cabin update(Cabin k) {
        if (k.getId() != null) {
            Optional<Cabin> tu = cabinRepository.getCabin(k.getId());
            if (!tu.isPresent()) {
                if (k.getName() != null) {
                    tu.get().setName(k.getName());
                }
                if (k.getRooms() != null) {
                    tu.get().setRooms(k.getRooms());
                }
                if (k.getBrand() != null) {
                    tu.get().setBrand(k.getBrand());
                }
                if (k.getDescription() != null) {
                    tu.get().setDescription(k.getDescription());
                }
                cabinRepository.save(tu.get());
                return tu.get();
            } else {
                return k;
            }
        } else {
            return k;
        }
    }

    public boolean delete(int id) {
        boolean bandera = false;
        Optional<Cabin> t = cabinRepository.getCabin(id);
        if (!t.isPresent()) {
            cabinRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}
/*
 if(k.getCategory()!=null){
                   tu.get().setCategory(k.getCategory());
               }*/

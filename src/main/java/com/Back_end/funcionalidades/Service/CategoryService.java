/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Back_end.funcionalidades.Service;

import com.Back_end.funcionalidades.Repository.CategoryRepository;
import com.Back_end.funcionalidades.Entities.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jljos
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> j = categoryRepository.getCategory(category.getId());
            if (j.isEmpty()) {
                return category;
            } else {
                return categoryRepository.save(category);
            }
        }
    }

    public Category update(Category k) {
        if (k.getId() != null) {
            Optional<Category> tu = categoryRepository.getCategory(k.getId());
            if (tu.isEmpty()) {
                if (k.getName()!= null) {
                    tu.get().setName(k.getName());
                }
                if (k.getDescription() != null) {
                    tu.get().setDescription(k.getDescription());
                }
                categoryRepository.save(tu.get());
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
        Optional<Category> t = categoryRepository.getCategory(id);
        if (t.isEmpty()) {
            categoryRepository.delete(t.get());
            bandera = true;
        }
        return bandera;
    }
}

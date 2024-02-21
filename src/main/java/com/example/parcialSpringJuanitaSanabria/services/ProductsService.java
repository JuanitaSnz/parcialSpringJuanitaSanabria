package com.example.parcialSpringJuanitaSanabria.services;

import com.example.parcialSpringJuanitaSanabria.entities.Products;
import com.example.parcialSpringJuanitaSanabria.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    public Products save(Products products){
        return productsRepository.save(products);
    }
    public Products findById(Integer id){
        Optional<Products> optional = productsRepository.findById(id);
        return optional.isPresent()? optional.get():null;
    }

    public List<Products> getProductsById(List<Integer> productIds) {
        return productsRepository.findAllById(productIds);
    }
    public void delete(Products products){
        productsRepository.delete(products);
    }

    public Products update(Products products){
        Optional<Products> optional= productsRepository.findById(products.getId());
        if(optional.isPresent()){
            return productsRepository.save(products);
        }
        return null;
    }
}

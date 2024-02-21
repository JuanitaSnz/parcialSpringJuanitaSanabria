package com.example.parcialSpringJuanitaSanabria.services;

import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import com.example.parcialSpringJuanitaSanabria.entities.Sales;
import com.example.parcialSpringJuanitaSanabria.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public List<Customers> findAll(){return customersRepository.findAll();}

    public Customers findById(Integer id){
        Optional<Customers> optional=customersRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public Customers save(Customers customer){
        return customersRepository.save(customer);
    }

    public void delete(Customers customer){
        customersRepository.delete(customer);
    }

    public List<Sales> getSales(Customers customer){
        return customer.getSales();
    }
}

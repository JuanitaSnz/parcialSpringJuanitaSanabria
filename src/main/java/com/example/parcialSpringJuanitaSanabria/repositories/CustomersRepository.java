package com.example.parcialSpringJuanitaSanabria.repositories;

import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,Integer> {
}

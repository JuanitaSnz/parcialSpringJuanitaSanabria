package com.example.parcialSpringJuanitaSanabria.repositories;

import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import com.example.parcialSpringJuanitaSanabria.entities.Products;
import com.example.parcialSpringJuanitaSanabria.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Integer> {

    @Query("SELECT s.customer FROM Sales s WHERE s.idSales = :idSale")
    Customers findCustomerByVentaId(@Param("idSale") Integer idSale);

    @Query("SELECT s.products FROM Sales s WHERE s.idSales = :idSale")
    List<Products> findProductosByVentaId(@Param("idSale") Integer idSale);
}


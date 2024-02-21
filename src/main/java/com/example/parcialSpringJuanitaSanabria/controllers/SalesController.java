package com.example.parcialSpringJuanitaSanabria.controllers;

import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import com.example.parcialSpringJuanitaSanabria.entities.Products;
import com.example.parcialSpringJuanitaSanabria.entities.Sales;
import com.example.parcialSpringJuanitaSanabria.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/customer/{idSale}")
    public ResponseEntity<?> getClientFromSale(@PathVariable Integer idSale) {
        Customers customer = salesService.getClientFromSale(idSale);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/{idSale}")
    public ResponseEntity<?> getProductsFromSale(@PathVariable Integer idSale) {
        List<Products> products = salesService.getproductsfromSale(idSale);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sales>> findAllSales() {
        List<Sales> sales = salesService.findAll();
        if (!sales.isEmpty()) {
            return ResponseEntity.ok(sales);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSaleById(@PathVariable Integer id) {
        Sales sale = salesService.findById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save/{idCustomer}")
    public ResponseEntity<?> saveSale(@RequestBody Sales sales, @PathVariable Integer idCustomer) {
        ResponseEntity<?> response = salesService.save(sales, idCustomer);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}


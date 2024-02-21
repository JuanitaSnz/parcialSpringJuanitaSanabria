package com.example.parcialSpringJuanitaSanabria.services;


import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import com.example.parcialSpringJuanitaSanabria.entities.Products;
import com.example.parcialSpringJuanitaSanabria.entities.Sales;
import com.example.parcialSpringJuanitaSanabria.repositories.ProductsRepository;
import com.example.parcialSpringJuanitaSanabria.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsService productsService;

    @Autowired
    private CustomersService customersService;

    public Customers getClientFromSale(Integer idSale){
        return salesRepository.findCustomerByVentaId(idSale);
    }

    public List<Products> getproductsfromSale(Integer idSale){
        return salesRepository.findProductosByVentaId(idSale);
    }

    public List<Sales> findAll(){
        return salesRepository.findAll();
    }

    public Sales findById(Integer id){
        Optional<Sales> optional = salesRepository.findById(id);
        return optional.isPresent()? optional.get():null;
    }


    public ResponseEntity<?> save(Sales sales, Integer idCustomer){

        Customers customer= customersService.findById(idCustomer);
        if (customer==null){
            return ResponseEntity.badRequest().body("Cliente no encontrado con ID: " + idCustomer);
        }
        List<Integer>productsIds = getIdProducts(sales);

        ResponseEntity<?> response = validateStock(productsIds);

        if (response.getStatusCode() != HttpStatus.OK) {
            return response;
        }

        sales.setProducts((List<Products>) response.getBody());
        sales.setCustomer(customer);

        updateStockProducts(sales.getProducts());

        salesRepository.save(sales);

        return ResponseEntity.ok("Venta creada exitosamente");

    }

    private List<Integer> getIdProducts(Sales sales) {

        return sales.getProducts().stream()
                .map(Products::getId)
                .collect(Collectors.toList());
    }

    private ResponseEntity<?> validateStock(List<Integer> productIds) {
        List<Products> products = productsService.getProductsById(productIds);

        if (products.size() != productIds.size()) {
            return ResponseEntity.badRequest().body("Al menos uno de los productos no fue encontrado.");
        }

        for (Products product : products) {
            if (product.getStock() <= 0) {
                return ResponseEntity.badRequest().body("Stock insuficiente para el producto con ID: " + product.getId());
            }
        }

        return ResponseEntity.ok(products);
    }

    private void updateStockProducts(List<Products> products) {
        for (Products product : products) {
            int newStock = product.getStock() - 1;
            product.setStock(newStock);
            // Puedes considerar agregar una lógica adicional aquí, como validar si el stock no puede ser negativo, etc.
            productsService.update(product);
        }
    }




}

package com.example.parcialSpringJuanitaSanabria.controllers;

import com.example.parcialSpringJuanitaSanabria.entities.Products;
import com.example.parcialSpringJuanitaSanabria.repositories.ProductsRepository;
import com.example.parcialSpringJuanitaSanabria.responses.ResponseHandler;
import com.example.parcialSpringJuanitaSanabria.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<Products> result= productsService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }

    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Object> save(@RequestBody Products products){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,productsService.save(products));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            Products products = productsService.findById(id);
            if(!products.equals(null)){
                productsService.delete(products);
                return ResponseHandler.generateResponse("Success", HttpStatus.ACCEPTED,products);
            }
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Object> update(@RequestBody Products products){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,productsService.update(products));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);

        }
    }
}

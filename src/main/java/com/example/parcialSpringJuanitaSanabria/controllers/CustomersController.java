package com.example.parcialSpringJuanitaSanabria.controllers;

import com.example.parcialSpringJuanitaSanabria.entities.Customers;
import com.example.parcialSpringJuanitaSanabria.responses.ResponseHandler;
import com.example.parcialSpringJuanitaSanabria.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    //Endpoint para encontrar todos los cliente:
    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<Customers> result= customersService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }

    }

    //Añadir un nuevo cliente:
    @PostMapping("/saveCustomer")
    public ResponseEntity<Object> save(@RequestBody Customers customer){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,customersService.save(customer));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            Customers bill= customersService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,bill);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            Customers customer = customersService.findById(id);
            if(!customer.equals(null)){
                customersService.delete(customer);
                return ResponseHandler.generateResponse("Success", HttpStatus.ACCEPTED,customer);
            }
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Success", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}

package com.example.parcialSpringJuanitaSanabria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProducts;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int stock;

    @ManyToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Sales>sales;

    public Products() {

    }

    public Integer getId() {
        return idProducts;
    }

    public void setId(Integer id) {
        this.idProducts = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

package com.example.parcialSpringJuanitaSanabria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sales")
public class Sales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSales;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(allowableValues ="E, C, O")
    private TypePay type_pay;




    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false,foreignKey = @ForeignKey(name="fk_bills_to_customer"))
    @JsonIgnore
    private Customers customer;

    @ManyToMany
    @JoinTable(
            name="sales_products",
            joinColumns = @JoinColumn(name="idProduct")

    )
    public List<Products> products;

    public Sales() {
        products=new ArrayList<>();
    }

    public Integer getIdSales() {
        return idSales;
    }

    public void setIdSales(Integer idSales) {
        this.idSales = idSales;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public TypePay getType_pay() {
        return type_pay;
    }

    public void setType_pay(TypePay type_pay) {
        this.type_pay = type_pay;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}

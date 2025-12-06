package com.example.order_service.Entity;

import jakarta.persistence.*;
import lombok.*;


@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Double getPrice() {
//        return price;
//    }


}


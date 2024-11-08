package com.example.restendpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a product for sale in the store and its associated details including stock
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column
    private String description;
    @Column
    private int stock;
    @Column
    private Category category;
    @ManyToMany(mappedBy = "products")  // "products" is the field name in the Order class
    private Set<Order> orders = new HashSet<>();

    public Product(String name, double price, String description,
                   int stock, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.category = category;
    }
}

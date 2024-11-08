package com.example.restendpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an order of a product tied to a specific user.
 */
@Entity(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "product_order",                    // The join table name
            joinColumns = @JoinColumn(name = "order_id"),   // Foreign key for the Order
            inverseJoinColumns = @JoinColumn(name = "product_id") // Foreign key for the Product
    )
    private Set<Product> products = new HashSet<>();
}

package com.example.restendpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a users return of an item including the reason for the return.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Return {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Product product;
    @OneToOne
    private Order order;
    @Column(nullable = false)
    private String details;

    public Return(User user, Product product, Order order, String details) {
        this.user = user;
        this.product = product;
        this.order = order;
        this.details = details;
    }
}


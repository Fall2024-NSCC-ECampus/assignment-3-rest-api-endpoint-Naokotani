package com.example.restendpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user generated review including the comment.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Product product;
    @Column(nullable = false)
    private String comment;

    public Review(User user, Product product, String comment) {
        this.user = user;
        this.product = product;
        this.comment = comment;
    }
}

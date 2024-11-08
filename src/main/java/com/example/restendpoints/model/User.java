package com.example.restendpoints.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a user including their address and currently active orders.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String username;
    @OneToMany
    private Set<Address> addresses;
    @OneToOne
    private Address defaultAddress;
    @ManyToMany
    private Set<Order> orders;

}

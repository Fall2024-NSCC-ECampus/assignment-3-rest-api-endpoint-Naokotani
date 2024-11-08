package com.example.restendpoints.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity that stores information about addresses
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    String postalCode;
    @Column(nullable = false)
    String province;

    public Address(String province, String postalCode, String city, String street) {
        this.province = province;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
    }


}

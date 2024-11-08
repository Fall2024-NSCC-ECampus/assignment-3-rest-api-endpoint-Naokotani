package com.example.restendpoints.repository;

import com.example.restendpoints.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReposity extends JpaRepository<Product, Long> {}

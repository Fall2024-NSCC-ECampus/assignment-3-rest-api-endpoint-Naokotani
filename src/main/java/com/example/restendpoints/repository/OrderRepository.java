package com.example.restendpoints.repository;

import com.example.restendpoints.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

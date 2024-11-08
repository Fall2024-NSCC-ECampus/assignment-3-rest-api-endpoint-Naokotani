package com.example.restendpoints.repository;

import com.example.restendpoints.model.Returns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRepository extends JpaRepository<Returns, Long> {}

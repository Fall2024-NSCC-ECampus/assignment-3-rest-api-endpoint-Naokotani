package com.example.restendpoints.repository;

import com.example.restendpoints.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

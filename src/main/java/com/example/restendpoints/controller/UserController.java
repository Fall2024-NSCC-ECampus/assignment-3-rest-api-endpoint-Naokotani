package com.example.restendpoints.controller;

import com.example.restendpoints.model.User;
import com.example.restendpoints.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link com.example.restendpoints.model.User}
 */
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a user.
     * @param user new user to be created.
     * @return returns the created user and Status OK.
     */
    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Lists all users.
     * @return a list of all users and response OK.
     */
    @GetMapping("/user/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Lists a single {@link User} by id.
     * @param id the id of hte user to list
     * @return a user object.
     */
    @GetMapping("/user/list/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an {@link User} by id
     * @param newUser the new user info
     * @param id the id of the user to be updated
     * @return the newly updated user.
     */
    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser,
                                           @PathVariable Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        existingUser.ifPresent(user -> {
            user.setId(id);
            userRepository.save(user);
        });
        return existingUser.map(_ -> new ResponseEntity<>(newUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * Deletes {@link User} by id.
     * @param id The id of the user to be deleted.
     * @return returns the deleted user.
     */
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(_ -> userRepository.deleteById(id));
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

package com.example.restendpoints.controller;

import com.example.restendpoints.model.Order;
import com.example.restendpoints.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link com.example.restendpoints.model.Order}
 */
@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Creates a new order.
     * @param order order to be created to be created.
     * @return the created order.
     */
    @PostMapping("/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * Lists all orders.
     * @return A list of existing orders.
     */
    @GetMapping("/order/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     *
     * @param id id of the order to be returned.
     * @return either the order, or NOT_FOUND http status if the order doesn't exist.
     */
    @GetMapping("/order/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes an order.
     * @param id id of the order to be deleted.
     * @return returns status OK if the order has been deleted successfull or NOT_FOUND if
     * order does not exist.
     */
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(_ -> orderRepository.deleteById(id));
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an order with new order details
     * @param newOrder The new order details.
     * @param id The idea of the order to be updated.
     * @return Either the new created order, or NOT_FOUND if the order doesn't exist.
     */
    @PutMapping("/order/update/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        existingOrder.ifPresent(order -> {
            order.setProducts(newOrder.getProducts());
            orderRepository.save(order);
        });
        return existingOrder.map(_ -> new ResponseEntity<>(newOrder, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

package com.example.restendpoints.controller;

import com.example.restendpoints.model.Product;
import com.example.restendpoints.repository.ProductReposity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link com.example.restendpoints.model.Product}
 */
@RestController
public class ProductController {
    private final ProductReposity productReposity;

    public ProductController(ProductReposity productReposity) {
        this.productReposity = productReposity;
    }

    /**
     * Creates a new product.
     * @param product product to be created to be created.
     * @return the created product.
     */
    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productReposity.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Lists all products.
     * @return A list of existing products.
     */
    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productReposity.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *
     * @param id id of the product to be returned.
     * @return either the product, or NOT_FOUND http status if the product doesn't exist.
     */
    @GetMapping("/product/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productReposity.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a product.
     * @param id id of the product to be deleted.
     * @return returns status OK if the product has been deleted successfull or NOT_FOUND if
     * product does not exist.
     */
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productReposity.findById(id);
        product.ifPresent(_ -> productReposity.deleteById(id));
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an product with new product details
     * @param newProduct The new product details.
     * @param id The idea of the product to be updated.
     * @return Either the new created product, or NOT_FOUND if the product doesn't exist.
     */
    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Optional<Product> existingProduct = productReposity.findById(id);
        existingProduct.ifPresent(product -> {
            newProduct.setId(product.getId());
            productReposity.save(newProduct);
        });
        return existingProduct.map(_ -> new ResponseEntity<>(newProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

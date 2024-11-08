package com.example.restendpoints.controller;

import com.example.restendpoints.model.Returns;
import com.example.restendpoints.model.Review;
import com.example.restendpoints.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link Review}
 */
@RestController
public class ReviewController {
    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Creates a new {@link Review}.
     * @param review review to be created to be created.
     * @return the created review.
     */
    @PostMapping("/review/create")
    public ResponseEntity<Review> createReturn(@RequestBody Review review) {
        reviewRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    /**
     * Lists all reviews.
     * @return A list of existing reviews.
     */
    @GetMapping("/review/list")
    public ResponseEntity<List<Review>> getAllReturns() {
        List<Review> reviews = reviewRepository.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    /**
     *
     * @param id id of the review to be returned.
     * @return either the review, or NOT_FOUND http status if the review doesn't exist.
     */
    @GetMapping("/review/get/{id}")
    public ResponseEntity<Review> getReturnById(@PathVariable Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a review.
     * @param id id of the review to be deleted.
     * @return returns status OK if the review has been deleted successfully or NOT_FOUND if
     * review does not exist.
     */
    @DeleteMapping("/review/delete/{id}")
    public ResponseEntity<Review> deleteReturn(@PathVariable Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        review.ifPresent(_ -> reviewRepository.deleteById(id));
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates a review with new review details
     * @param newReview The new review details.
     * @param id The idea of the review to be updated.
     * @return Either the new created review, or NOT_FOUND if the review doesn't exist.
     */
    @PutMapping("/review/update/{id}")
    public ResponseEntity<Review> updateReturn(@RequestBody Review newReview,
                                               @PathVariable Long id) {
        Optional<Review> existingReturn = reviewRepository.findById(id);
        existingReturn.ifPresent(review -> {
            newReview.setId(review.getId());
            reviewRepository.save(review);
        });
        return existingReturn.map(_ -> new ResponseEntity<>(newReview, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
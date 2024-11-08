package com.example.restendpoints.controller;

import com.example.restendpoints.model.Returns;
import com.example.restendpoints.repository.ReturnRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link com.example.restendpoints.model.Returns}
 */
@RestController
public class ReturnController {
    private final ReturnRepository  returnRepository;

    public ReturnController(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    /**
     * Creates a new {@link Returns}.
     * @param returnOrder return to be created to be created.
     * @return the created return.
     */
    @PostMapping("/return/create")
    public ResponseEntity<Returns> createReturns(@RequestBody Returns returnOrder) {
        returnRepository.save(returnOrder);
        return new ResponseEntity<>(returnOrder, HttpStatus.CREATED);
    }

    /**
     * Lists all returns.
     * @return A list of existing returns.
     */
    @GetMapping("/return/list")
    public ResponseEntity<List<Returns>> getAllReturnss() {
        List<Returns> returns = returnRepository.findAll();
        return new ResponseEntity<>(returns, HttpStatus.OK);
    }

    /**
     *
     * @param id id of the return to be returned.
     * @return either the return, or NOT_FOUND http status if the return doesn't exist.
     */
    @GetMapping("/return/get/{id}")
    public ResponseEntity<Returns> getReturnsById(@PathVariable Long id) {
        Optional<Returns> returnOrder = returnRepository.findById(id);
        return returnOrder.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a return.
     * @param id id of the return to be deleted.
     * @return returns status OK if the return has been deleted successfully or NOT_FOUND if
     * return does not exist.
     */
    @DeleteMapping("/return/delete/{id}")
    public ResponseEntity<Returns> deleteReturns(@PathVariable Long id) {
        Optional<Returns> returnOrder = returnRepository.findById(id);
        returnOrder.ifPresent(_ -> returnRepository.deleteById(id));
        return returnOrder.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an return with new return details
     * @param newReturns The new return details.
     * @param id The idea of the return to be updated.
     * @return Either the new created return, or NOT_FOUND if the return doesn't exist.
     */
    @PutMapping("/return/update/{id}")
    public ResponseEntity<Returns> updateReturns(@RequestBody Returns newReturns,
                                               @PathVariable Long id) {
        Optional<Returns> existingReturns = returnRepository.findById(id);
        existingReturns.ifPresent(returnOrder -> {
            newReturns.setId(returnOrder.getId());
            returnRepository.save(newReturns);
        });
        return existingReturns.map(_ -> new ResponseEntity<>(newReturns, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}


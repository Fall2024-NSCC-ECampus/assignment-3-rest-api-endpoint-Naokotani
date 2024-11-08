package com.example.restendpoints.controller;

import com.example.restendpoints.model.Return;
import com.example.restendpoints.repository.ReturnRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CRUD controller for {@link com.example.restendpoints.model.Return}
 */
@RestController
public class ReturnController {
    private final ReturnRepository  returnRepository;

    public ReturnController(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    /**
     * Creates a new {@link Return}.
     * @param returnOrder return to be created to be created.
     * @return the created return.
     */
    @PostMapping("/return/create")
    public ResponseEntity<Return> createReturn(@RequestBody Return returnOrder) {
        returnRepository.save(returnOrder);
        return new ResponseEntity<>(returnOrder, HttpStatus.CREATED);
    }

    /**
     * Lists all returns.
     * @return A list of existing returns.
     */
    @GetMapping("/return/list")
    public ResponseEntity<List<Return>> getAllReturns() {
        List<Return> returns = returnRepository.findAll();
        return new ResponseEntity<>(returns, HttpStatus.OK);
    }

    /**
     *
     * @param id id of the return to be returned.
     * @return either the return, or NOT_FOUND http status if the return doesn't exist.
     */
    @GetMapping("/return/get/{id}")
    public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
        Optional<Return> returnOrder = returnRepository.findById(id);
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
    public ResponseEntity<Return> deleteReturn(@PathVariable Long id) {
        Optional<Return> returOrder = returnRepository.findById(id);
        returOrder.ifPresent(_ -> returnRepository.deleteById(id));
        return returOrder.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an return with new return details
     * @param newReturn The new return details.
     * @param id The idea of the return to be updated.
     * @return Either the new created return, or NOT_FOUND if the return doesn't exist.
     */
    @PutMapping("/return/update/{id}")
    public ResponseEntity<Return> updateReturn(@RequestBody Return newReturn,
                                               @PathVariable Long id) {
        Optional<Return> existingReturn = returnRepository.findById(id);
        existingReturn.ifPresent(returnOrder -> {
            newReturn.setId(returnOrder.getId());
            returnRepository.save(newReturn);
        });
        return existingReturn.map(_ -> new ResponseEntity<>(newReturn, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}


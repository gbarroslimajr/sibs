package com.sibs.api.controller;

import com.sibs.api.dto.InputStockMovementDTO;
import com.sibs.business.interfaces.StockMovementService;
import com.sibs.core.UtilBuildResponse;
import com.sibs.domain.model.StockMovement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author geraldobarrosjr
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/stockmovement")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockMovement>> findAllStockMovement() {
        return  ResponseEntity.ok(stockMovementService.findAllStockMovements());
    }
    @GetMapping("/item/{item}")
    public ResponseEntity<List<StockMovement>> findStockMovementByItem(@PathVariable String item) {
        return  ResponseEntity.ok(stockMovementService.findByItem_Name(item));
    }

    @GetMapping( "/{id}")
    public ResponseEntity<StockMovement> findItemById(@PathVariable Long id) {
        return  ResponseEntity.ok(stockMovementService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<StockMovement> createStockMovement(@Valid @RequestBody InputStockMovementDTO stockMovementInput) {
        StockMovement stockMovementCreated = stockMovementService.create(stockMovementInput);
        return  ResponseEntity.created(UtilBuildResponse.getURILocation(stockMovementCreated.getId())).body(stockMovementCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovement> updateStockMovement(@PathVariable Long id,  @Valid @RequestBody InputStockMovementDTO StockMovementInput) {
        return  ResponseEntity.ok().body(stockMovementService.update(id, StockMovementInput));
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStockMovement(@PathVariable Long id) {
        stockMovementService.delete(id);
    }

}

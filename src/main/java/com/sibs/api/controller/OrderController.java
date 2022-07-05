package com.sibs.api.controller;

import com.sibs.api.dto.InputoOrderDTO;
import com.sibs.business.interfaces.OrderService;
import com.sibs.core.UtilBuildResponse;
import com.sibs.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all orders", description = "Get all orders")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> findAllOrders() {
        return  ResponseEntity.ok(orderService.findAllItems());
    }

    @Operation(summary = "Get order by item ", description = "Get order by item name")
    @GetMapping("/item/{item}")
    public ResponseEntity<List<Order>> findOrderByItem(@PathVariable String item) {
        return  ResponseEntity.ok(orderService.findByItem_Name(item));
    }

    @Operation(summary = "Get order by Id ", description = "Get order by Id")
    @GetMapping( "/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        return  ResponseEntity.ok(orderService.findById(id));
    }

    @Operation(summary = "Get pending orders ", description = "Get pending orders")
    @GetMapping("/pending")
    public ResponseEntity<List<Order>> findOrdersPending() {
        return  ResponseEntity.ok(orderService.findOrdersPending());
    }

    @Operation(summary = "Get completed orders ", description = "Get completed orders")
    @GetMapping("/completed")
    public ResponseEntity<List<Order>> findOrdersCompleted() {
        return  ResponseEntity.ok(orderService.findOrdersCompleted());
    }

    @Operation(summary = "Create a order ", description = "Create a order")
    @PostMapping()
    public ResponseEntity<Order> createOrder(@Valid @RequestBody InputoOrderDTO orderInput) {
        Order orderCreated = orderService.create(orderInput);
        return  ResponseEntity.created(UtilBuildResponse.getURILocation(orderCreated.getId())).body(orderCreated);
    }

    @Operation(summary = "Update a order ", description = "Update a order")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,  @Valid @RequestBody InputoOrderDTO orderInput) {
        return  ResponseEntity.ok().body(orderService.update(id, orderInput));
    }

    @Operation(summary = "Delete a order ", description = "Delete a order")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }
}

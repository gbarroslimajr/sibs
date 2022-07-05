package com.sibs.api.controller;

import com.sibs.api.dto.ItemDTO;
import com.sibs.business.interfaces.ItemService;
import com.sibs.core.UtilBuildResponse;
import com.sibs.domain.model.Item;
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
@RequestMapping("/v1/item")

public class ItemController {

    private final ItemService itemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> findAllItems() {
        return  ResponseEntity.ok(itemService.findAllItems());
    }

    @GetMapping( "/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Long id) {

        return  ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping( "/name/{name}")
    public ResponseEntity<List<Item>> findItemByName(@PathVariable String name) {

        return  ResponseEntity.ok(itemService.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDTO itemInput) {
        Item itemCreated = itemService.create(itemInput);
        return  ResponseEntity.created(UtilBuildResponse.getURILocation(itemCreated.getId())).body(itemCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,  @Valid @RequestBody ItemDTO itemInput) {
        return  ResponseEntity.ok().body(itemService.update(id, itemInput));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }
}

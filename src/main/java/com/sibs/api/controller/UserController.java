package com.sibs.api.controller;

import com.sibs.api.dto.UserDTO;
import com.sibs.business.interfaces.UserService;
import com.sibs.core.UtilBuildResponse;
import com.sibs.domain.model.User;
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
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAllUsers() {
       return  ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping( "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {

        return  ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping( "/name/{name}")
    public ResponseEntity<List<User>> findUserByName(@PathVariable String name) {

        return  ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping( "/email/{email}")
    public ResponseEntity<List<User>> findUserByEmail(@PathVariable String email) {

        return  ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userInput) {
        User userCreated = userService.create(userInput);
        return  ResponseEntity.created(UtilBuildResponse.getURILocation(userCreated.getId())).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,  @Valid @RequestBody UserDTO userInput) {
        return  ResponseEntity.ok().body(userService.update(id, userInput));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }


}

package vhslab.solution.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.solution.entities.dto.UserEntityDto;
import vhslab.solution.entities.model.UserEntity;
import vhslab.solution.service.UserEntityService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserEntityService userService;

    @PostMapping
    public ResponseEntity<UserEntityDto> createUser(@RequestBody UserEntityDto user) {
        UserEntityDto createdUser = userService.createUser(user);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntityDto> getUser(@PathVariable Long userId) {
        UserEntityDto user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntityDto> updateUser(@PathVariable Long userId, @RequestBody UserEntityDto updatedUser) {
        UserEntityDto updated = userService.updateUser(userId, updatedUser);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

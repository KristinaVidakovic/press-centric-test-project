package com.presscentric.presscentrictestproject.controller;

import com.presscentric.presscentrictestproject.model.User;
import com.presscentric.presscentrictestproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> findAll () {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "")
    @Transactional
    public ResponseEntity<Void> create (@Valid @RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getById (@PathVariable Integer userId) {
        User user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}")
    @Transactional
    public ResponseEntity<Void> update (@PathVariable Integer userId,
                                        @Valid @RequestBody User user) {
        userService.update(user, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteById (@PathVariable Integer userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package org.telbot.telran.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telbot.telran.info.model.User;
import org.telbot.telran.info.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private User user;

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
        try {
            return userService.getUser(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        try {
            return userService.save(user);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") int id) {
        try {
            userService.delete(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}

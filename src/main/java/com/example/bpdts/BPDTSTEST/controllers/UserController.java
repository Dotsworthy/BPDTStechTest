package com.example.bpdts.BPDTSTEST.controllers;

import com.example.bpdts.BPDTSTEST.models.User;
import com.example.bpdts.BPDTSTEST.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public Optional<User> getUser(@PathVariable Long id) {

        return userRepository.findById(id);
    }

    @GetMapping(value = "/{city}/users")
    public ResponseEntity<List<User>> findUsersFilterByCity(
            @RequestParam(name = "city", required = false) String city) {
        if (city != null) {
            return new ResponseEntity<>(userRepository.findUserByCity(city), HttpStatus.OK);
        }
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}

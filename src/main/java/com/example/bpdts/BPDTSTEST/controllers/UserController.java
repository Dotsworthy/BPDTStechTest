package com.example.bpdts.BPDTSTEST.controllers;

import com.example.bpdts.BPDTSTEST.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAllUsersAndFilters(
            @RequestParam(name="city", required=false) String city,
            @RequestParam(name="id", required=false) Long id
        ){
        if (id != null) {
           return new ResponseEntity(userRepository.findUserById(id), HttpStatus.OK);
        }
        if (city != null) {
            return new ResponseEntity(userRepository.findUserByCity(city), HttpStatus.OK);
        }
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }
}

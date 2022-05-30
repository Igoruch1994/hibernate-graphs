package com.ihoruch.controller;

import com.ihoruch.dto.UserRegistrationDto;
import com.ihoruch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.create(userRegistrationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

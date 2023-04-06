package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.LoginDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fashion-blog/")
@Log4j2
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
        log.info("Creating User");
        UserDto response = userService.registerUser(userDto);
        log.info("User registration is successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> loginUser(@RequestBody @Valid LoginDto loginDto) {
        log.info("Creating User");
        UserDto response = userService.loginUser(loginDto);
        log.info("User login successful");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}

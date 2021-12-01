package com.okay.controller;

import com.okay.model.UserDto;
import com.okay.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("test")
    @GetMapping(value = "/get")
    public ResponseEntity<UserDto> test1(@Valid @RequestParam(name = "userId") long userId) {
        return new ResponseEntity<>(userService.get(userId), HttpStatus.OK);
    }
}
package com.okay.controller;

import com.okay.model.UserDto;
import com.okay.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<UserDto>> list() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    //    @PreAuthorize("test")
    @GetMapping(value = "/get")
    public ResponseEntity<UserDto> test1(@Valid @RequestParam(name = "userId") long userId) {
        return new ResponseEntity<>(userService.get(userId), HttpStatus.OK);
    }
}
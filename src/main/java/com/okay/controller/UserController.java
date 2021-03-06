package com.okay.controller;

import com.okay.model.UserDto;
import com.okay.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/save")
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
package com.okay.controller;

import com.okay.model.UserDto;
import com.okay.service.AuthService;
import com.okay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<UserDto> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserDto userDto = authService.login(username, password);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }
}
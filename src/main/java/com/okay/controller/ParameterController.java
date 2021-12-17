package com.okay.controller;

import com.okay.model.RequestDto;
import com.okay.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameter")
public class ParameterController {

    private RequestService service;

    public ParameterController(RequestService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/request")
    public ResponseEntity<RequestDto> requestTest() {
        return new ResponseEntity<>(service.get(1), HttpStatus.OK);
    }
}
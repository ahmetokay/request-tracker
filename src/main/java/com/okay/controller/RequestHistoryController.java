package com.okay.controller;

import com.okay.model.RequestHistoryDto;
import com.okay.service.RequestHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request-history")
public class RequestHistoryController {

    private RequestHistoryService service;

    public RequestHistoryController(RequestHistoryService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RequestHistoryDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/list")
    public ResponseEntity<List<RequestHistoryDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/filter/{requestId}")
    public ResponseEntity<List<RequestHistoryDto>> filter(@PathVariable("requestId") long requestId) {
        return new ResponseEntity<>(service.filter(requestId), HttpStatus.OK);
    }
}
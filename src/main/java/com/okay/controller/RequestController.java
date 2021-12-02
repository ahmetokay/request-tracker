package com.okay.controller;

import com.okay.model.RequestDto;
import com.okay.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RequestDto> save(@RequestBody RequestDto request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<RequestDto> update(@RequestBody RequestDto request) {
        return new ResponseEntity<>(service.update(request), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RequestDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RequestDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
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

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RequestDto> save(@RequestBody RequestDto request) {
        return new ResponseEntity<>(requestService.save(request), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<RequestDto> update(@RequestBody RequestDto request) {
        return new ResponseEntity<>(requestService.update(request), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{requestId}")
    public ResponseEntity<RequestDto> get(@PathVariable("requestId") long requestId) {
        return new ResponseEntity<>(requestService.get(requestId), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RequestDto>> list() {
        return new ResponseEntity<>(requestService.list(), HttpStatus.OK);
    }
}
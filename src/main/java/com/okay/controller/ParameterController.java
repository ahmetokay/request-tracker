package com.okay.controller;

import com.okay.model.RequestTypeDto;
import com.okay.model.ResponseTypeDto;
import com.okay.service.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parameter")
public class ParameterController {

    private ParameterService service;

    public ParameterController(ParameterService service) {
        this.service = service;
    }

    @GetMapping(value = "/request-type")
    public ResponseEntity<List<RequestTypeDto>> requestTypeList() {
        return new ResponseEntity<>(service.requestTypeList(), HttpStatus.OK);
    }

    @GetMapping(value = "/response-type")
    public ResponseEntity<List<ResponseTypeDto>> responseTypeList() {
        return new ResponseEntity<>(service.responseTypeList(), HttpStatus.OK);
    }
}
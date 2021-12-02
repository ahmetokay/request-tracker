package com.okay.controller;

import com.okay.model.RequestHistoryDto;
import com.okay.service.RequestHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request-history")
public class RequestHistoryController {

    private RequestHistoryService requestHistoryService;

    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RequestHistoryDto> save(@RequestBody RequestHistoryDto requestHistory) {
        return new ResponseEntity<>(requestHistoryService.save(requestHistory), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<RequestHistoryDto> update(@RequestBody RequestHistoryDto requestHistory) {
        return new ResponseEntity<>(requestHistoryService.update(requestHistory), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RequestHistoryDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(requestHistoryService.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RequestHistoryDto>> list() {
        return new ResponseEntity<>(requestHistoryService.list(), HttpStatus.OK);
    }
}
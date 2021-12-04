package com.okay.controller;

import com.okay.model.MenuDto;
import com.okay.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/save")
    public ResponseEntity<MenuDto> save(@RequestBody MenuDto request) {
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/update")
    public ResponseEntity<MenuDto> update(@RequestBody MenuDto request) {
        return new ResponseEntity<>(service.update(request), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<MenuDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/list")
    public ResponseEntity<List<MenuDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
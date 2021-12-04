package com.okay.controller;

import com.okay.model.WorkspaceDto;
import com.okay.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private WorkspaceService service;

    public WorkspaceController(WorkspaceService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/save")
    public ResponseEntity<WorkspaceDto> save(@RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(service.save(workspace), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @PostMapping(value = "/update")
    public ResponseEntity<WorkspaceDto> update(@RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(service.update(workspace), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<WorkspaceDto> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.okay.constant.RoleConstants).ROLE_USER)")
    @GetMapping(value = "/list")
    public ResponseEntity<List<WorkspaceDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
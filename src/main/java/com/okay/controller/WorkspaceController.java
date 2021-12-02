package com.okay.controller;

import com.okay.model.WorkspaceDto;
import com.okay.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<WorkspaceDto> save(@RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(workspaceService.save(workspace), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<WorkspaceDto> update(@RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(workspaceService.update(workspace), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{workspaceId}")
    public ResponseEntity<WorkspaceDto> get(@PathVariable("workspaceId") long workspaceId) {
        return new ResponseEntity<>(workspaceService.get(workspaceId), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<WorkspaceDto>> list() {
        return new ResponseEntity<>(workspaceService.list(), HttpStatus.OK);
    }
}
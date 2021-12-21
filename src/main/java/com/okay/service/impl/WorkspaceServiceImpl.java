package com.okay.service.impl;

import com.okay.converter.WorkspaceConverter;
import com.okay.entity.Workspace;
import com.okay.model.WorkspaceDto;
import com.okay.repository.WorkspaceRepository;
import com.okay.service.RequestService;
import com.okay.service.UserService;
import com.okay.service.WorkspaceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkspaceServiceImpl implements WorkspaceService {

    private final UserService userService;

    private final RequestService requestService;

    private final WorkspaceRepository repository;

    private final WorkspaceConverter converter;

    public WorkspaceServiceImpl(UserService userService, RequestService requestService, WorkspaceRepository repository, WorkspaceConverter converter) {
        this.userService = userService;
        this.requestService = requestService;
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public WorkspaceDto save(WorkspaceDto workspace) {
        workspace.setUser(userService.getCurrentUser());
        return converter.convertToDto(repository.save(converter.convertToEntity(workspace)));
    }

    @Override
    public WorkspaceDto update(WorkspaceDto workspace) {
        return converter.convertToDto(repository.save(converter.convertToEntity(workspace)));
    }

    @Override
    public boolean delete(WorkspaceDto workspace) {
        Workspace workspaceEntity = converter.convertToEntity(workspace);
        repository.delete(workspaceEntity);
        return true;
    }

    @Override
    public WorkspaceDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<WorkspaceDto> list() {
        return converter.convertToDtoList(repository.findAll());
    }
}
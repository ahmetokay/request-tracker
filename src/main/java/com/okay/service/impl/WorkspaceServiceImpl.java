package com.okay.service.impl;

import com.okay.converter.WorkspaceConverter;
import com.okay.model.WorkspaceDto;
import com.okay.repository.WorkspaceRepository;
import com.okay.service.WorkspaceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkspaceServiceImpl implements WorkspaceService {

    private WorkspaceRepository repository;

    private WorkspaceConverter converter;

    public WorkspaceServiceImpl(WorkspaceRepository repository, WorkspaceConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public WorkspaceDto save(WorkspaceDto workspace) {
        return converter.convertToDto(repository.save(converter.convertToEntity(workspace)));
    }

    @Override
    public WorkspaceDto update(WorkspaceDto workspace) {
        return converter.convertToDto(repository.save(converter.convertToEntity(workspace)));
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
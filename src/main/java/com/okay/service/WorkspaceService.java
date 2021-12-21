package com.okay.service;

import com.okay.model.WorkspaceDto;

import java.util.List;

public interface WorkspaceService {

    WorkspaceDto save(WorkspaceDto workspace);

    WorkspaceDto update(WorkspaceDto workspace);

    boolean delete(WorkspaceDto workspace);

    WorkspaceDto get(long id);

    List<WorkspaceDto> list();
}
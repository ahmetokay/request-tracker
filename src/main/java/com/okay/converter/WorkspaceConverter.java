package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.Workspace;
import com.okay.model.WorkspaceDto;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceConverter extends AbstractBaseConverter<WorkspaceDto, Workspace> {
}
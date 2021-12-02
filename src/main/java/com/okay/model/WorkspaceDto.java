package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class WorkspaceDto extends BaseModel {

    private UserDto user;

    private String name;

    private List<RequestDto> requestList;
}
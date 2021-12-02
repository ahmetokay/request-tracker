package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

@Data
public class RequestDto extends BaseModel {

    private RequestWorkspaceDto workspace;

    private RequestTypeDto requestType;

    private ScheduledTypeDto scheduledType;

    private String url;

    private String port;
}
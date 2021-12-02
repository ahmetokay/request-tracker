package com.okay.model;

import com.okay.core.BaseModel;
import com.okay.enm.EnumScheduledType;
import lombok.Data;

@Data
public class RequestDto extends BaseModel {

    private RequestWorkspaceDto workspace;

    private RequestTypeDto requestType;

    private EnumScheduledType scheduledType;

    private String url;

    private String port;
}
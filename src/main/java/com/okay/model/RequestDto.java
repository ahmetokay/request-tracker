package com.okay.model;

import com.okay.core.BaseModel;
import com.okay.enm.EnumProtocolType;
import com.okay.enm.EnumRequestType;
import com.okay.enm.EnumScheduledType;
import lombok.Data;

@Data
public class RequestDto extends BaseModel {

    private RequestWorkspaceDto workspace;

    private EnumRequestType requestType;

    private EnumScheduledType scheduledType;

    private EnumProtocolType protocol;

    private String name;

    private String url;

    private String port;

    private String body;

    private Integer tryCount;
}
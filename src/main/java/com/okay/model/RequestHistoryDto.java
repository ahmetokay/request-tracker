package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class RequestHistoryDto extends BaseModel {

    private RequestDto request;

    private ResponseTypeDto responseType;

    private Date requestDate;
}
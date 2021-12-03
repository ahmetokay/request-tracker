package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class RequestHistoryDto extends BaseModel {

    private RequestDto request;

    private Integer responseCode;

    private String body;

    private Date requestDate;
}
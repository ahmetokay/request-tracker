package com.okay.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseModel implements Serializable {

    private Long id;

    private Boolean active;

    private Date created;

    private Integer createdBy;

    private Date updated;

    private Integer updatedBy;
}
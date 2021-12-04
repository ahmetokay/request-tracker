package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

@Data
public class MenuDto extends BaseModel {

    private RoleDto role;

    private String name;

    private Integer ordering;
}
package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends BaseModel {

    private String email;

    private String name;

    private String surname;

    private List<RoleDto> roleList;
}
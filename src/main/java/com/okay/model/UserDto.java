package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Data;

@Data
public class UserDto extends BaseModel {

    private String email;

    private String name;

    private String surname;
}
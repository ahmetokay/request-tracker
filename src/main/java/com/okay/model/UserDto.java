package com.okay.model;

import com.okay.core.BaseModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto extends BaseModel {

    private String email;

    private String password;

    private String name;

    private String surname;
}
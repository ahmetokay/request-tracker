package com.okay.service;

import com.okay.entity.User;
import com.okay.model.UserDto;

public interface AuthService {

    UserDto login(String username, String password);
}

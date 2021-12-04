package com.okay.service;

import com.okay.model.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByEmail(String email);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    UserDto get(long id);

    List<UserDto> list();
}
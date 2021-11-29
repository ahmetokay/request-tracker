package com.okay.service.impl;

import com.okay.model.UserDto;
import com.okay.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private List<UserDto> userList;

    public UserServiceImpl() {
        userList = new ArrayList<>();
        userList.add(UserDto.builder().name("Ahmet1").surname("Okay1").build());
        userList.add(UserDto.builder().name("Ahmet2").surname("Okay2").build());
        userList.add(UserDto.builder().name("Ahmet3").surname("Okay3").build());
        userList.add(UserDto.builder().name("Ahmet4").surname("Okay4").build());
        userList.add(UserDto.builder().name("Ahmet5").surname("Okay5").build());
    }

    @Override
    public UserDto get(long userId) {
        return userList.get(0);
    }

    @Override
    public List<UserDto> filter(String name, String surname) {
        //TODO not filtering
        return userList;
    }
}
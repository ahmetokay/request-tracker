package com.okay.service.impl;

import com.okay.converter.UserConverter;
import com.okay.model.UserDto;
import com.okay.repository.UserRepository;
import com.okay.service.UserService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserConverter converter;

    public UserServiceImpl(UserRepository repository, UserConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UserDto findByUsername(String username) {
        return converter.convertToDto(repository.findByUsername(username));
    }

    @Override
    public UserDto save(UserDto user) {
        return converter.convertToDto(repository.save(converter.convertToEntity(user)));
    }

    @Override
    public UserDto update(UserDto user) {
        return converter.convertToDto(repository.save(converter.convertToEntity(user)));
    }

    @Override
    public UserDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<UserDto> list() {
        return converter.convertToDtoList(repository.findAll());
    }


}
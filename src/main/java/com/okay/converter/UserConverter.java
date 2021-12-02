package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.User;
import com.okay.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractBaseConverter<UserDto, User> {
}
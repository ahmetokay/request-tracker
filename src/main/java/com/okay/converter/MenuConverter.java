package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.Menu;
import com.okay.model.MenuDto;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter extends AbstractBaseConverter<MenuDto, Menu> {
}
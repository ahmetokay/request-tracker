package com.okay.service;

import com.okay.model.MenuDto;

import java.util.List;

public interface MenuService {

    MenuDto save(MenuDto menu);

    MenuDto update(MenuDto menu);

    MenuDto get(long id);

    List<MenuDto> list();
}
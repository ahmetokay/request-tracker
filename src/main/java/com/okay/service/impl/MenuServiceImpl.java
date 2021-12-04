package com.okay.service.impl;

import com.okay.converter.MenuConverter;
import com.okay.entity.Menu;
import com.okay.model.MenuDto;
import com.okay.repository.MenuRepository;
import com.okay.service.MenuService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;

    private final MenuConverter converter;

    public MenuServiceImpl(MenuRepository repository, MenuConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public MenuDto save(MenuDto menu) {
        return converter.convertToDto(repository.save(converter.convertToEntity(menu)));
    }

    @Override
    public MenuDto update(MenuDto menu) {
        return converter.convertToDto(repository.save(converter.convertToEntity(menu)));
    }

    @Override
    public MenuDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<MenuDto> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        List<Menu> menuList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            List<Menu> list = repository.findByActiveIsTrueAndRoleName(authority.getAuthority());
            if (list != null && list.size() > 0) {
                menuList.addAll(list);
            }
        }

        menuList.sort(Comparator.comparing(Menu::getOrdering));

        return converter.convertToDtoList(menuList);
    }
}
package com.okay.repository;

import com.okay.core.BaseRepository;
import com.okay.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> findByActiveIsTrueAndRoleName(String name);
}
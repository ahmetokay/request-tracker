package com.okay.repository;

import com.okay.core.BaseRepository;
import com.okay.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByEmail(String email);
}
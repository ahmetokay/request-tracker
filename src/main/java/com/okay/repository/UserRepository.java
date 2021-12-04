package com.okay.repository;

import com.okay.core.BaseRepository;
import com.okay.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User findByUsername(String username);
}
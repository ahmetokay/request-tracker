package com.okay.repository;

import com.okay.core.BaseRepository;
import com.okay.enm.EnumScheduledType;
import com.okay.entity.Request;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends BaseRepository<Request> {

    List<Request> findByActiveIsTrueAndScheduledType(EnumScheduledType scheduledType);
}
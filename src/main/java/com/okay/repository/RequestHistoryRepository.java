package com.okay.repository;

import com.okay.core.BaseRepository;
import com.okay.entity.Request;
import com.okay.entity.RequestHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestHistoryRepository extends BaseRepository<RequestHistory> {

    List<RequestHistory> findByRequestId(Long requestId);
}
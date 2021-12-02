package com.okay.service;

import com.okay.model.RequestHistoryDto;

import java.util.List;

public interface RequestHistoryService {

    RequestHistoryDto save(RequestHistoryDto requestHistory);

    RequestHistoryDto update(RequestHistoryDto requestHistory);

    RequestHistoryDto get(long id);

    List<RequestHistoryDto> list();

    List<RequestHistoryDto> filter(long requestId);
}
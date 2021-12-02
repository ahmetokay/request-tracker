package com.okay.service.impl;

import com.okay.converter.RequestHistoryConverter;
import com.okay.model.RequestHistoryDto;
import com.okay.repository.RequestHistoryRepository;
import com.okay.service.RequestHistoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestHistoryServiceImpl implements RequestHistoryService {

    private RequestHistoryRepository repository;

    private RequestHistoryConverter converter;

    public RequestHistoryServiceImpl(RequestHistoryRepository repository, RequestHistoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RequestHistoryDto save(RequestHistoryDto request) {
        return null;
    }

    @Override
    public RequestHistoryDto update(RequestHistoryDto request) {
        return null;
    }

    @Override
    public RequestHistoryDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<RequestHistoryDto> list() {
        return null;
    }

    @Override
    public List<RequestHistoryDto> filter(long requestId) {
        return converter.convertToDtoList(repository.findAll());
    }
}
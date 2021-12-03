package com.okay.service.impl;

import com.okay.converter.RequestHistoryConverter;
import com.okay.model.RequestHistoryDto;
import com.okay.repository.RequestHistoryRepository;
import com.okay.service.RequestHistoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestHistoryServiceImpl implements RequestHistoryService {

    private final RequestHistoryRepository repository;

    private final RequestHistoryConverter converter;

    public RequestHistoryServiceImpl(RequestHistoryRepository repository, RequestHistoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RequestHistoryDto save(RequestHistoryDto requestHistory) {
        return converter.convertToDto(repository.save(converter.convertToEntity(requestHistory)));
    }

    @Override
    public RequestHistoryDto update(RequestHistoryDto requestHistory) {
        return converter.convertToDto(repository.save(converter.convertToEntity(requestHistory)));
    }

    @Override
    public RequestHistoryDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<RequestHistoryDto> list() {
        return converter.convertToDtoList(repository.findAll());
    }

    @Override
    public List<RequestHistoryDto> filter(long requestId) {
        return converter.convertToDtoList(repository.findByRequestId(requestId));
    }
}
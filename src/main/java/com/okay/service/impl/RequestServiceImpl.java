package com.okay.service.impl;

import com.okay.converter.RequestConverter;
import com.okay.model.RequestDto;
import com.okay.repository.RequestRepository;
import com.okay.service.RequestService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestServiceImpl implements RequestService {

    private RequestRepository repository;

    private RequestConverter converter;

    public RequestServiceImpl(RequestRepository repository, RequestConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RequestDto save(RequestDto request) {
        return null;
    }

    @Override
    public RequestDto update(RequestDto request) {
        return null;
    }

    @Override
    public RequestDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<RequestDto> list() {
        return converter.convertToDtoList(repository.findAll());
    }
}
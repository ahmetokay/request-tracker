package com.okay.service.impl;

import com.okay.converter.RequestConverter;
import com.okay.enm.EnumScheduledType;
import com.okay.model.RequestDto;
import com.okay.repository.RequestRepository;
import com.okay.service.RequestService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    private final RequestConverter converter;

    public RequestServiceImpl(RequestRepository repository, RequestConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RequestDto save(RequestDto request) {
        return converter.convertToDto(repository.save(converter.convertToEntity(request)));
    }

    @Override
    public RequestDto update(RequestDto request) {
        return converter.convertToDto(repository.save(converter.convertToEntity(request)));
    }

    @Override
    public RequestDto get(long id) {
        return converter.convertToDto(repository.findById(id).get());
    }

    @Override
    public List<RequestDto> list() {
        return converter.convertToDtoList(repository.findAll());
    }

    @Override
    public List<RequestDto> filter(EnumScheduledType scheduledType) {
        return converter.convertToDtoList(repository.findByActiveIsTrueAndScheduledType(scheduledType));
    }
}
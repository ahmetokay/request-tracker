package com.okay.service.impl;

import com.okay.converter.RequestConverter;
import com.okay.enm.EnumScheduledType;
import com.okay.entity.Request;
import com.okay.model.RequestDto;
import com.okay.repository.RequestHistoryRepository;
import com.okay.repository.RequestRepository;
import com.okay.service.RequestService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    private final RequestHistoryRepository requestHistoryRepository;

    private final RequestConverter converter;

    public RequestServiceImpl(RequestRepository repository, RequestHistoryRepository requestHistoryRepository, RequestConverter converter) {
        this.repository = repository;
        this.requestHistoryRepository = requestHistoryRepository;
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

    @Transactional
    @Override
    public boolean delete(RequestDto request) {
        Request requestEntity = converter.convertToEntity(request);
        requestHistoryRepository.deleteAllByRequest(requestEntity);
        repository.delete(requestEntity);
        return true;
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
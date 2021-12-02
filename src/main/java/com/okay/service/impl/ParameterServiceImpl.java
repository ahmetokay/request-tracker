package com.okay.service.impl;

import com.okay.converter.RequestTypeConverter;
import com.okay.converter.ResponseTypeConverter;
import com.okay.converter.ScheduledTypeConverter;
import com.okay.model.RequestTypeDto;
import com.okay.model.ResponseTypeDto;
import com.okay.model.ScheduledTypeDto;
import com.okay.repository.RequestTypeRepository;
import com.okay.repository.ResponseTypeRepository;
import com.okay.repository.ScheduledTypeRepository;
import com.okay.service.ParameterService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParameterServiceImpl implements ParameterService {

    private ScheduledTypeRepository scheduledTypeRepository;

    private RequestTypeRepository requestTypeRepository;

    private ResponseTypeRepository responseTypeRepository;

    private ScheduledTypeConverter scheduledTypeConverter;

    private RequestTypeConverter requestTypeConverter;

    private ResponseTypeConverter responseTypeConverter;

    public ParameterServiceImpl(ScheduledTypeRepository scheduledTypeRepository, RequestTypeRepository requestTypeRepository, ResponseTypeRepository responseTypeRepository, ScheduledTypeConverter scheduledTypeConverter, RequestTypeConverter requestTypeConverter, ResponseTypeConverter responseTypeConverter) {
        this.scheduledTypeRepository = scheduledTypeRepository;
        this.requestTypeRepository = requestTypeRepository;
        this.responseTypeRepository = responseTypeRepository;
        this.scheduledTypeConverter = scheduledTypeConverter;
        this.requestTypeConverter = requestTypeConverter;
        this.responseTypeConverter = responseTypeConverter;
    }

    @Override
    public List<ScheduledTypeDto> scheduledTypeList() {
        return scheduledTypeConverter.convertToDtoList(scheduledTypeRepository.findAll());
    }

    @Override
    public List<RequestTypeDto> requestTypeList() {
        return requestTypeConverter.convertToDtoList(requestTypeRepository.findAll());
    }

    @Override
    public List<ResponseTypeDto> responseTypeList() {
        return responseTypeConverter.convertToDtoList(responseTypeRepository.findAll());
    }
}
package com.okay.service.impl;

import com.okay.converter.RequestTypeConverter;
import com.okay.converter.ResponseTypeConverter;
import com.okay.model.RequestTypeDto;
import com.okay.model.ResponseTypeDto;
import com.okay.repository.RequestTypeRepository;
import com.okay.repository.ResponseTypeRepository;
import com.okay.service.ParameterService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParameterServiceImpl implements ParameterService {

    private RequestTypeRepository requestTypeRepository;

    private ResponseTypeRepository responseTypeRepository;

    private RequestTypeConverter requestTypeConverter;

    private ResponseTypeConverter responseTypeConverter;

    public ParameterServiceImpl(RequestTypeRepository requestTypeRepository, ResponseTypeRepository responseTypeRepository, RequestTypeConverter requestTypeConverter, ResponseTypeConverter responseTypeConverter) {
        this.requestTypeRepository = requestTypeRepository;
        this.responseTypeRepository = responseTypeRepository;
        this.requestTypeConverter = requestTypeConverter;
        this.responseTypeConverter = responseTypeConverter;
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
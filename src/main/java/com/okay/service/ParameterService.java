package com.okay.service;

import com.okay.model.RequestTypeDto;
import com.okay.model.ResponseTypeDto;

import java.util.List;

public interface ParameterService {

    List<RequestTypeDto> requestTypeList();

    List<ResponseTypeDto> responseTypeList();
}
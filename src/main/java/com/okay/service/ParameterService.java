package com.okay.service;

import com.okay.model.RequestTypeDto;
import com.okay.model.ResponseTypeDto;
import com.okay.model.ScheduledTypeDto;

import java.util.List;

public interface ParameterService {

    List<ScheduledTypeDto> scheduledTypeList();

    List<RequestTypeDto> requestTypeList();

    List<ResponseTypeDto> responseTypeList();
}
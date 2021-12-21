package com.okay.service;

import com.okay.enm.EnumScheduledType;
import com.okay.model.RequestDto;

import java.util.List;

public interface RequestService {

    RequestDto save(RequestDto request);

    RequestDto update(RequestDto request);

    boolean delete(RequestDto request);

    RequestDto get(long id);

    List<RequestDto> list();

    List<RequestDto> filter(EnumScheduledType requestType);
}
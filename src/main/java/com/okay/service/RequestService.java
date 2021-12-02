package com.okay.service;

import com.okay.model.RequestDto;

import java.util.List;

public interface RequestService {

    RequestDto save(RequestDto request);

    RequestDto update(RequestDto request);

    RequestDto get(long id);

    List<RequestDto> list();
}
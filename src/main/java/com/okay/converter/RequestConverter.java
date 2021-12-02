package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.Request;
import com.okay.model.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class RequestConverter extends AbstractBaseConverter<RequestDto, Request> {
}
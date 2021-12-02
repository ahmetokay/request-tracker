package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.RequestType;
import com.okay.model.RequestTypeDto;
import org.springframework.stereotype.Component;

@Component
public class RequestTypeConverter extends AbstractBaseConverter<RequestTypeDto, RequestType> {
}
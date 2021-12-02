package com.okay.converter;

import com.okay.core.AbstractBaseConverter;
import com.okay.entity.RequestHistory;
import com.okay.model.RequestHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class RequestHistoryConverter extends AbstractBaseConverter<RequestHistoryDto, RequestHistory> {
}
package com.okay.scheduled;

import com.okay.enm.EnumScheduledType;
import com.okay.model.RequestDto;
import com.okay.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledJob {

    private Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    private RequestService requestService;

    public ScheduledJob(RequestService requestService) {
        this.requestService = requestService;
    }

    //    @Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 * * * * ?")
    public void perform() throws Exception {
        List<RequestDto> list = requestService.filter(EnumScheduledType.MIN_15);
        LOGGER.info(list.toString());
    }
}

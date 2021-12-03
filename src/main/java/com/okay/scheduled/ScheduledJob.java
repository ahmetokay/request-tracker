package com.okay.scheduled;

import com.okay.enm.EnumScheduledType;
import com.okay.model.RequestDto;
import com.okay.model.RequestHistoryDto;
import com.okay.service.RequestHistoryService;
import com.okay.service.RequestService;
import com.okay.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledJob {

    private Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    private final RequestService requestService;

    private final RequestHistoryService requestHistoryService;

    public ScheduledJob(RequestService requestService, RequestHistoryService requestHistoryService) {
        this.requestService = requestService;
        this.requestHistoryService = requestHistoryService;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void interval1Min() {
        processInterval(EnumScheduledType.MIN1);
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void interval15Min() {
        processInterval(EnumScheduledType.MIN15);
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void interval30Min() {
        processInterval(EnumScheduledType.MIN30);
    }

    @Scheduled(cron = "0 0/60 * * * ?")
    public void interval60Min() {
        processInterval(EnumScheduledType.MIN60);
    }

    private void processInterval(EnumScheduledType scheduledType) {
        int successCount = 0, failCount = 0;
        List<RequestDto> requestList = requestService.filter(scheduledType);
        for (RequestDto request : requestList) {
            RequestHistoryDto requestHistory = RequestUtils.sendRequest(requestHistoryService, request);
            if (requestHistory.getResponseCode() == 200) {
                successCount++;
            } else {
                failCount++;
            }
        }
        LOGGER.info("Interval {} job worked with {} request(s): [ {} success / {} error ]", scheduledType, requestList.size(), successCount, failCount);
    }
}
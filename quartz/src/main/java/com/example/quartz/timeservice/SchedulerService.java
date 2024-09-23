package com.example.quartz.timeservice;

import com.example.quartz.info.TimeInfo;
import com.example.quartz.listener.TrialJobListener;
import com.example.quartz.util.TimeUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SchedulerService {
    private final Scheduler scheduler;

    public void schedule(Class jobClass, TimeInfo timeInfo) throws SchedulerException {
        JobDetail jobDetail= TimeUtils.buildJobDetail(jobClass,timeInfo);
        Trigger buildTrigger=TimeUtils.buildTrigger(jobClass,timeInfo);
        scheduler.scheduleJob(jobDetail,buildTrigger);
        scheduler.getListenerManager().addJobListener(new TrialJobListener());
    }



    @PostConstruct
    public void init() throws SchedulerException{
            scheduler.start();
            log.info("start");
    }
    @PreDestroy
    public void preDestroy() throws SchedulerException{
            scheduler.shutdown();
            log.info("end");
    }
}

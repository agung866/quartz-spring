package com.example.quartz.runjob;

import com.example.quartz.info.TimeInfo;
import com.example.quartz.jobs.TrialJobs;
import com.example.quartz.timeservice.SchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RunJobs {
    private final SchedulerService schedulerService;

    public void runJob() throws SchedulerException {
        TimeInfo timeInfo= new TimeInfo();
        timeInfo.setTotalFireCount(5);
        timeInfo.setRepeatIntervalMs(2000);
        timeInfo.setInitialOffsetMs(10000);//
        timeInfo.setCallbackData("My Callback Data");

        schedulerService.schedule(TrialJobs.class,timeInfo);
    }
}

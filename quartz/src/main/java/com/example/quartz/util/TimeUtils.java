package com.example.quartz.util;

import com.example.quartz.info.TimeInfo;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

public class TimeUtils {
    private TimeUtils(){}

    public static JobDetail buildJobDetail(Class jobClass, TimeInfo timeInfo){
        JobDataMap jobDataMap =new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(),timeInfo);

        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(Class jobClass, TimeInfo timeInfo){
        SimpleScheduleBuilder builder=SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMilliseconds(timeInfo.getRepeatIntervalMs());

        if(timeInfo.isRunForever()){
            builder=builder.repeatForever();
        }else{
            builder=builder.withRepeatCount(timeInfo.getTotalFireCount()-1);
        }
        return TriggerBuilder.newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+timeInfo.getInitialOffsetMs()))
                .build();
    }

}

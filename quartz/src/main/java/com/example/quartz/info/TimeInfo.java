package com.example.quartz.info;

import lombok.Data;

@Data
public class TimeInfo {
    private int totalFireCount;
    private  boolean runForever;
    private long repeatIntervalMs;
    private long initialOffsetMs;
    private String callbackData;
    private String jobName;
}

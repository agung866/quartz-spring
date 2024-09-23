package com.example.quartz.controller;

import com.example.quartz.runjob.RunJobs;
import lombok.AllArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/v1")
@AllArgsConstructor
public class JobController {
    private final RunJobs jobs;

    @PostMapping("/trial")
    private void runJob() throws SchedulerException {
        jobs.runJob();
    }
}

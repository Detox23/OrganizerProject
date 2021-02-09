//package com.example.src.configurations;
//
//import com.example.src.utilities.ATestJob;
//import lombok.AllArgsConstructor;
//import lombok.var;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AllArgsConstructor
//public class JobConfig {
//
//
//    @Bean
//    public JobDetail aTestJob(){
//        return JobBuilder.newJob(ATestJob.class).withIdentity("testjob").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger aTestTrigger(JobDetail jobadetails) throws SchedulerException {
//        var trigger = TriggerBuilder.newTrigger().forJob(jobadetails)
//                .withIdentity("ATestJobtrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
//                .build();
//
//        return trigger;
//    }
//
//}

package com.example.src.configurations;

import com.example.src.services.JobService;
import lombok.AllArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JobConfig {

    @Autowired // or @Inject
    private JobScheduler jobScheduler;

    @Autowired // or @Inject
    private JobService sampleJobService;


    @Bean
    public void aTestJob() {
        jobScheduler.enqueue(() -> sampleJobService.execute());
    }

}

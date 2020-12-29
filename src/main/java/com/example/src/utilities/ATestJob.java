package com.example.src.utilities;

import com.example.src.services.TaskService;
import lombok.AllArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ATestJob extends QuartzJobBean {

    private TaskService _taskService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            System.out.println("MAKING TASKS PASSED");
            _taskService.passedTasks();
        }catch (Exception e){
            throw new JobExecutionException(e);
        }
    }
}

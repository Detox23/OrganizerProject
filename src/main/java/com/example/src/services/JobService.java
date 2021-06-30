package com.example.src.services;

import com.example.src.entities.Task;
import com.example.src.repositories.ITaskRepository;
import lombok.AllArgsConstructor;
import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobService {

    private final ITaskRepository _iTaskRepository;

    @Job()
    public void executePassedTasks() {
        try{
            var notPassedTasks =  _iTaskRepository.getAllByPassedIsFalse();
            notPassedTasks.parallelStream().forEach(x -> {
                x.setPassed(true);
                _iTaskRepository.save(x);
            });
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

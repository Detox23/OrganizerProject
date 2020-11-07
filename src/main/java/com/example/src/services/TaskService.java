package com.example.src.services;

import com.example.src.entities.Task;
import com.example.src.repositories.ITaskRepository;
import com.example.src.repositories.IUserRepository;
import com.example.src.utilities.GetLoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final ITaskRepository _iTaskRepository;

    private final IUserRepository _iUserRepository;

    @Transactional(rollbackOn = Exception.class)
    public Task createTask(Task task){
        try{
            var result = _iTaskRepository.save(task);
            return result;
        }catch(Exception exception){
            return null;

        }
    }

    public ArrayList<Task> getAllTasks(){
        try{
            GetLoggedUser getLoggedUser = new GetLoggedUser(_iUserRepository);
            return _iTaskRepository.getAllByUserIs(getLoggedUser.getCurrentUser());
        }catch (Exception exception){
            return null;
        }
    }

    public ArrayList<Task> getTasksForDay(LocalDateTime date){
        try{
            GetLoggedUser getLoggedUser = new GetLoggedUser(_iUserRepository);
            var endDate = date.plusDays(1L);
            return _iTaskRepository.getAllByUserIsAndStartTimeAfterAndEndTimeBefore(
                    getLoggedUser.getCurrentUser(),
                    date,
                    endDate
            );
        }catch (Exception exception){
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ArrayList<Task> deleteTask(UUID taskId){
        try{
            _iTaskRepository.deleteById(taskId);
            return getAllTasks();
        }catch (Exception exception){
            return null;
        }
    }



}

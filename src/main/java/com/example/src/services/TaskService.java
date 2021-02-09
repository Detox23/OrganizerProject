package com.example.src.services;

import com.example.src.entities.Task;
import com.example.src.repositories.ITaskRepository;
import com.example.src.repositories.IUserRepository;
import com.example.src.utilities.DateFormatter;
import com.example.src.utilities.GetLoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.var;
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
    public ArrayList<Task> createTask(Task task){
        try{
            var result = _iTaskRepository.save(task);
            var toReturn = getTasksForDay(DateFormatter.getLocalDateTimeFromString(
                    result.getStartTime().toLocalDate().toString()));
            return toReturn;
        }catch(Exception exception){
            return null;

        }
    }


    @Async
    public boolean passedTasks(){
        try{
            var notPassedTasks =  _iTaskRepository.getAllByPassedIsFalse();
            notPassedTasks.parallelStream().forEach(x -> {
                x.setPassed(true);
                _iTaskRepository.save(x);
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * Method that returns all tasks for logged user.
     *
     * @return All tasks
     */
    public ArrayList<Task> getAllTasks(){
        try{
            GetLoggedUser getLoggedUser = new GetLoggedUser(_iUserRepository);
            var tasks = _iTaskRepository.getAllByUserIs(getLoggedUser.getCurrentUser());
            return tasks;
        }catch (Exception exception){
            return null;
        }
    }

    public ArrayList<Task> getTasksForDay(LocalDateTime date){
        try{
            GetLoggedUser getLoggedUser = new GetLoggedUser(_iUserRepository);
            var currentUser = getLoggedUser.getCurrentUser();
            var endDate = date.plusDays(1L);
            var result = _iTaskRepository.getAllByUserIsAndStartTimeAfterAndEndTimeBeforeOrderByStartTime(
                    currentUser,
                    date,
                    endDate
            );
            return result;
        }catch (Exception exception){
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ArrayList<Task> deleteTask(UUID taskId){
        try{
            _iTaskRepository.deleteById(taskId);
            var tasks =  getAllTasks();
            return tasks;
        }catch (Exception exception){
            return null;
        }
    }



}

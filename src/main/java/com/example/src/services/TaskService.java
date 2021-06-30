package com.example.src.services;

import com.amazonaws.services.devicefarm.model.ArgumentException;
import com.example.src.dtos.TaskDto;
import com.example.src.entities.Task;
import com.example.src.repositories.ITaskRepository;
import com.example.src.repositories.IUserRepository;
import com.example.src.utilities.GetLoggedUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class TaskService {

    private final ITaskRepository _iTaskRepository;

    private final ModelMapper modelMapper;

    private final IUserRepository _iUserRepository;


    @Transactional(rollbackOn = Exception.class)
    public TaskDto createTask(Task task){
        try{
            var user = new GetLoggedUser(_iUserRepository).getCurrentUser();
            var allExisting = _iTaskRepository.getAllByDateIsAndUserIs(task.getDate(), user);
            if(validateTaskDates(task.getStartTime(), task.getEndTime(), allExisting)){
                var added = _iTaskRepository.save(task);
                return modelMapper.map(added, TaskDto.class);
            }else{
                throw new ArgumentException(String.format(
                        "There already exists a task within hours %s and %s.",
                        task.getStartTime().toString(), task.getEndTime().toString()
                ));
            }
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    private boolean validateTaskDates(LocalTime start, LocalTime end, ArrayList<Task> tasks){
        AtomicBoolean result = new AtomicBoolean(true);
        tasks.parallelStream().forEach( x->{
            if(start.isBefore(x.getEndTime()) && end.isBefore(x.getStartTime())){
                result.set(false);
            }
        });
        return result.get();
    }


    /**
     * Method that returns all tasks for logged user.
     *
     * @return All tasks
     */
    public ArrayList<TaskDto> getAllTasks(){
        try{
            var user = new GetLoggedUser(_iUserRepository).getCurrentUser();
            var found = _iTaskRepository.getAllByUserIs(user);
            found.sort(Comparator.comparing(Task::getStartTime));
            return (ArrayList<TaskDto>) modelMapper.map(found, new TypeToken<ArrayList<TaskDto>>() {}.getType());
        }catch (Exception exception){
            return null;
        }
    }

    public ArrayList<TaskDto> getTasksForDay(LocalDate date){
        try{
            var user = new GetLoggedUser(_iUserRepository).getCurrentUser();
            var found = _iTaskRepository.getAllByDateIsAndUserIs(date, user);
            found.sort(Comparator.comparing(Task::getStartTime));
            return (ArrayList<TaskDto>) modelMapper.map(found, new TypeToken<ArrayList<TaskDto>>() {}.getType());
        }catch (Exception exception){
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean deleteTask(UUID taskId){
        try{
            _iTaskRepository.deleteById(taskId);
            return true;
        }catch (Exception exception){
            return false;
        }
    }



}

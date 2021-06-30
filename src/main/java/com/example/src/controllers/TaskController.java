package com.example.src.controllers;

import com.example.src.dtos.TaskForCreation;
import com.example.src.entities.Task;
import com.example.src.services.TaskService;
import com.example.src.utilities.DateFormatter;
import com.example.src.utilities.ResponseCreator;
import lombok.var;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService _taskService;

    private final ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody TaskForCreation task){
        var mappedTask =  modelMapper.map(task, Task.class);
        var result = _taskService.createTask(mappedTask);
        return ResponseCreator.createResponseMessage(result, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllTasks(){
        var result = _taskService.getAllTasks();
        return ResponseCreator.createResponseMessage(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getTasksForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        var result = _taskService.getTasksForDay(date);
        return ResponseCreator.createResponseMessage(result, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        var result = _taskService.deleteTask(id);
        return ResponseCreator.createResponseMessage(result, HttpStatus.NO_CONTENT);
    }




}

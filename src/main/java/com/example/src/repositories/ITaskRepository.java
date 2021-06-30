package com.example.src.repositories;

import com.example.src.entities.Task;
import com.example.src.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID> {
    ArrayList<Task> getAllByPassedIsFalse();
    ArrayList<Task> getAllByUserIs(User user);
    ArrayList<Task> getAllByDateIsAndUserIs(LocalDate date, User user);
    ArrayList<Task> getAllByUserIsAndStartTimeAfterAndEndTimeBeforeOrderByStartTime(User user, LocalDateTime start, LocalDateTime end);
}

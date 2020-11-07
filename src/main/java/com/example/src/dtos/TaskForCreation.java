package com.example.src.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskForCreation {
    public String description;
    public String title;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
}

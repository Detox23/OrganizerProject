package com.example.src.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskForCreation {
    public String description;
    public String title;
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
}

package com.example.src.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String description;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Boolean passed;
}

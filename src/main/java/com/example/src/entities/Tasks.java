package com.example.src.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "Tasks")
public class Tasks{

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="description", nullable=false)
    private String description;


    @Column(name="title")
    @NotBlank(message="Name is required")
    private String title;


}

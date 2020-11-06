package com.example.src.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(schema = "public", name= "tasks")
public class Tasks{

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id = UUID.randomUUID();


    @Column(name="description", nullable=false)
    private String description;


    @Column(name="title")
    @NotBlank(message="Name is required")
    private String title;


}

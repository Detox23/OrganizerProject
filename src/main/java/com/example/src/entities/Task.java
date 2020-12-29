package com.example.src.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "public", name= "tasks")
public class Task{

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id = UUID.randomUUID();

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="start_time", nullable=false)
    private LocalDateTime startTime;

    @Column(name="end_time", nullable=false)
    private LocalDateTime endTime;

    @Builder.Default
    @Column(name="passed")
    private Boolean passed = false;

    @CreatedDate
    @Column(name="created_date", nullable= false, updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name="last_modified_data", nullable = false)
    private LocalDateTime modified;

    @CreatedBy
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="crete_user_id", referencedColumnName = "id")
    private User user;

    @LastModifiedBy
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="modify_user_id", referencedColumnName = "id")
    private User modifiedBy;

}

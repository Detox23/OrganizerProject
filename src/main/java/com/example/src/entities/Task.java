package com.example.src.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "public", name= "tasks")
public class Task{

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GenericGenerator(name = "UUID", strategy="org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="start_time", nullable=false)
    private LocalTime startTime;

    @Column(name="end_time", nullable=false)
    private LocalTime endTime;

    @Column(name="date", nullable = false)
    private LocalDate date;

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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="crete_user_id", referencedColumnName = "id")
    private User user;

    @LastModifiedBy
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="modify_user_id", referencedColumnName = "id")
    private User modifiedBy;

}

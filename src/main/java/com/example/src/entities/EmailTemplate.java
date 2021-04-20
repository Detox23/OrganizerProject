package com.example.src.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(
        schema = "public",
        name = "email_template")
public class EmailTemplate implements Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GenericGenerator(name = "UUID", strategy="org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime modified;

    @Column(name = "type")
    private EmailTemplateType type;

    @Column(name="content", columnDefinition="TEXT", nullable = false)
    private String content;
}
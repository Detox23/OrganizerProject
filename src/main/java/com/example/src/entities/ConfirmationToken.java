package com.example.src.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name="confirmation_tokens")
public class ConfirmationToken {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GenericGenerator(name = "UUID", strategy="org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name="confirmation_token", nullable=false)
    private String confirmationToken = UUID.randomUUID().toString();


    @Column(name="created_date", nullable=false)
    private LocalDate createdDate = LocalDate.now();

}

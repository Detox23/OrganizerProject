package com.example.src.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private UUID id = UUID.randomUUID();

    @Column(name="confirmation_token", nullable=false)
    private String confirmationToken = UUID.randomUUID().toString();


    @Column(name="created_date", nullable=false)
    private LocalDate createdDate = LocalDate.now();

}

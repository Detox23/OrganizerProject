package com.example.src.entities;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;
import java.time.LocalDate;
import javax.persistence.*;

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

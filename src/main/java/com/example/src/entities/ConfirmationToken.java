package com.example.src.entities;

import lombok.*;
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
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id = UUID.randomUUID();

    @Column(name="confirmation_token", nullable=false)
    private String confirmationToken;


    @Column(name="created_date", nullable=false)
    private LocalDate createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    private User user;

    public ConfirmationToken(User user){
        this.user = user;
        this.createdDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }


}

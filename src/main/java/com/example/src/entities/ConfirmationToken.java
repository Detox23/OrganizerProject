package com.example.src.entities;

import lombok.*;
import java.util.UUID;
import java.time.LocalDate;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ConfirmationTokens")
public class ConfirmationToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="confirmationToken", nullable=false)
    private String confirmationToken;


    @Column(name="createdDate", nullable=false)
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

package com.example.src.repositories;

import com.example.src.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IConfirmationTokenRepository extends CrudRepository<ConfirmationToken, UUID> {
    Optional<ConfirmationToken> findByConfirmationTokenIs(String token);
}

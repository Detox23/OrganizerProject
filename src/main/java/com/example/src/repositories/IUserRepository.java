package com.example.src.repositories;

import com.example.src.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {

    @Async
    Optional<User> findByEmail(String email);


}

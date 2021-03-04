package com.example.src.repositories;

import com.example.src.entities.ConfirmationToken;
import com.example.src.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByConfirmationTokenIs(ConfirmationToken token);

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    Optional<User> findByEmail(String email);


}

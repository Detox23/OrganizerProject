package com.example.src.repositories;

import com.example.src.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

}

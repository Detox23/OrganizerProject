package com.example.src.repositories;

import com.example.src.entities.GreetingDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<GreetingDb, Long> {


}

package com.example.src.repositories;

import com.example.src.entities.EmailTemplate;
import com.example.src.entities.EmailTemplateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmailTemplateRepository extends JpaRepository<EmailTemplate, UUID> {

    EmailTemplate getByTypeIs(EmailTemplateType type);
}

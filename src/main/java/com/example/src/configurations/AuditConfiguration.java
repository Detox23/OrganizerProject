package com.example.src.configurations;

import com.example.src.entities.User;
import com.example.src.utilities.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public AuditorAware<User> auditorProvider(){
        return new AuditorAwareImpl();
    }


}

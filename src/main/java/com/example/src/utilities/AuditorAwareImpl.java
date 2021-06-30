package com.example.src.utilities;

import com.example.src.entities.User;
import com.example.src.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private IUserRepository _iUserRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return _iUserRepository.findByEmail("admin@gmail.com");
        }

        var username = authentication.getPrincipal().toString();
        return _iUserRepository.findByEmail(username);
    }
}

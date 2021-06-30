package com.example.src.utilities;

import com.example.src.entities.User;
import com.example.src.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class GetLoggedUser {

    private final IUserRepository _iUserRepository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        var username = authentication.getPrincipal().toString();
        var user = _iUserRepository.findByEmail(username);
        return user.orElse(null);
    }
}
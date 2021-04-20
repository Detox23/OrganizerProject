package com.example.src.utilities;

import lombok.var;
import org.modelmapper.ModelMapper;
import com.example.src.entities.User;
import com.example.src.dtos.UserForCreation;
import com.example.src.repositories.IUserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private IUserRepository _iUserRepository;

    @Autowired
    private ModelMapper _modelMapper;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        var username = authentication.getPrincipal().toString();
        var returnUser = _iUserRepository.findByEmail(username);
        return returnUser;

    }
}

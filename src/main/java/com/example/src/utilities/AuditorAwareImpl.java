package com.example.src.utilities;

import com.example.src.dtos.UserForCreation;
import com.example.src.entities.User;
import com.example.src.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

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
            var adminUser = _iUserRepository.findByEmail("theticker5@gmail.com");
            if (adminUser.isPresent()) {
                return adminUser;
            } else {
                var saveResult = _iUserRepository.save(
                        _modelMapper.map(
                                new UserForCreation(
                                        "admin",
                                        "admin",
                                        "theticker5@gmail.com",
                                        "90809988Qwe."
                                ),
                                User.class
                        )
                );
                return Optional.of(saveResult);
            }

        }else{
            var username = authentication.getPrincipal().toString();
            var returnUser = _iUserRepository.findByEmail(username);
            return returnUser;
        }
    }
}

package com.example.src.services;

import com.example.src.entities.ConfirmationToken;
import com.example.src.entities.User;
import com.example.src.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder _bCryptPasswordEncoder;

    private final EmailService _emailService;

    private final IUserRepository _iUserRepository;

    private final ConfirmationTokenService _confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = _iUserRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User signUpUser(User user){
        final String encryptedPassword = _bCryptPasswordEncoder.encode(user.getPassword());
        final ConfirmationToken confirmationToken = new ConfirmationToken();
        var result = _confirmationTokenService.saveConfirmationToken(confirmationToken);
        user.setPassword(encryptedPassword);
        user.setConfirmationToken(confirmationToken);
        final User createdUser = _iUserRepository.save(user);
        if(result){
            _emailService.sendMessage(user.getEmail(), "Invitation", String.format("localhost:8080/api/auth/%s", confirmationToken.getConfirmationToken()));
        }
        return createdUser;
    }

    public String acceptUser(String confirmationToken){
        var token = _confirmationTokenService.getTokenId(confirmationToken);
        if(token == null){
            return "Confirmation token was not found.";
        }
        var confirmationUser = _iUserRepository.findByConfirmationTokenIs(token);
        if(confirmationUser.isPresent()){
            var confirmationUserFound = confirmationUser.get();
            confirmationUserFound.setEnabled(true);
            _iUserRepository.save(confirmationUserFound);
            return "User accepted.";
        }else{
            return "User to accept was not found.";
        }
    }

    public String blockUser(String userId){
        var userToBlock = _iUserRepository.findById(UUID.fromString(userId));
        if(userToBlock.isPresent()){
            var userToBlockResult = userToBlock.get();
            userToBlockResult.setLocked(true);
            _iUserRepository.save(userToBlockResult);
            return "User was blocked.";
        }else{
            return "User does not exist.";
        }
    }

//    public void confirmUser(ConfirmationToken confirmationToken){
//        final User user = confirmationToken.getUser();
//        user.setEnabled(true);
//        _iUserRepository.save(user);
//        _confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
//    }

}

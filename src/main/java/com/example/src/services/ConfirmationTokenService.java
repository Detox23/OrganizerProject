package com.example.src.services;


import com.example.src.entities.ConfirmationToken;
import com.example.src.repositories.IConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final IConfirmationTokenRepository _iConfirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        _iConfirmationTokenRepository.save(confirmationToken);
    }

    public void deleteConfirmationToken(Long id){
        _iConfirmationTokenRepository.deleteById(id);
    }



}

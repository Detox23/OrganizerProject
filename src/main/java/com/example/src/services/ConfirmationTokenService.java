package com.example.src.services;

import com.example.src.entities.ConfirmationToken;
import com.example.src.repositories.IConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final IConfirmationTokenRepository _iConfirmationTokenRepository;

    public boolean saveConfirmationToken(ConfirmationToken confirmationToken){
        var result = _iConfirmationTokenRepository.save(confirmationToken);
        return result != null;
    }

    public void deleteConfirmationToken(Long id){
//        _iConfirmationTokenRepository.deleteById(id);
    }



}

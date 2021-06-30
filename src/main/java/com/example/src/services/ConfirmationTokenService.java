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
        try{
            _iConfirmationTokenRepository.save(confirmationToken);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public ConfirmationToken getTokenId(String token){
        try{
            var foundToken = _iConfirmationTokenRepository.findByConfirmationTokenIs(token);
            return foundToken.orElse(null);
        }catch (Exception exception){
            return null;
        }
    }



//    public void deleteConfirmationToken(Long id){
//        _iConfirmationTokenRepository.deleteById(id);
//    }



}

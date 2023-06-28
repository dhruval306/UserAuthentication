package com.user.userAuthenticationApplication.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository tokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        tokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}

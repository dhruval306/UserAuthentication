package com.user.userAuthenticationApplication.registration;

import com.user.userAuthenticationApplication.AppUser.AppUser;
import com.user.userAuthenticationApplication.AppUser.AppUserRole;
import com.user.userAuthenticationApplication.AppUser.AppUserService;
import com.user.userAuthenticationApplication.registration.token.ConfirmationToken;
import com.user.userAuthenticationApplication.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValid = emailValidator.test(request.getEmail());

        if(!isValid){
            throw new IllegalStateException("Email is not valid");
        }

        appUserService.signUpUser( new AppUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));

        return "Works";
    }

    @Transactional
    public String confirmToken(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already verified");
        }

        if(confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());


        return "Confirmed";
    }
}

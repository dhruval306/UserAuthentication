package com.user.userAuthenticationApplication.registration;

import com.user.userAuthenticationApplication.AppUser.AppUser;
import com.user.userAuthenticationApplication.AppUser.AppUserRole;
import com.user.userAuthenticationApplication.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
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
}

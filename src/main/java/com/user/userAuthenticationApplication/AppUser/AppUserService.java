package com.user.userAuthenticationApplication.AppUser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()-> new IllegalStateException("User Not Found"));
    }

    public String signUpUser(AppUser appUser){
       boolean isExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
       if(isExist){
           throw new IllegalStateException("email is already taken");
       }
       appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
       appUserRepository.save(appUser);

       //TODO: Send Confirmation token

       return "User Added";
    }
}

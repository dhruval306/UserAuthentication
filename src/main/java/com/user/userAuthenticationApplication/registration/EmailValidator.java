package com.user.userAuthenticationApplication.registration;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class EmailValidator implements Predicate<String>{
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public boolean test(String email) {
        return email.matches(EMAIL_REGEX);
    }
}

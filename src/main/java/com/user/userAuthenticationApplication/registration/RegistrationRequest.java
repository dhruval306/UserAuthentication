package com.user.userAuthenticationApplication.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String name;
    private final  String email;
    private final String password;

}

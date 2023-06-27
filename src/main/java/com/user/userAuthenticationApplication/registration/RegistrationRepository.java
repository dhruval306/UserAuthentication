package com.user.userAuthenticationApplication.registration;

import com.user.userAuthenticationApplication.AppUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<AppUser, Long> {


}

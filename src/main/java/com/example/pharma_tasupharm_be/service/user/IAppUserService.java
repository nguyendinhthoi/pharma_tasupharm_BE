package com.example.pharma_tasupharm_be.service.user;

import com.example.pharma_tasupharm_be.model.user.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends UserDetailsService {
    AppUser findByUsername(String userName);

    Boolean createNewAppUser(AppUser appUser, String roleAdmin);

    AppUser findByEmail(String email);
}

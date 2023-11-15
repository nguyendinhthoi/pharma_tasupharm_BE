package com.example.pharma_tasupharm_be.service.user;

import com.example.pharma_tasupharm_be.dto.user.JwtResponseUserDetails;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.model.user.UserRole;
import com.example.pharma_tasupharm_be.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByName(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User name or password is wrong");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        for (UserRole userRole : appUser.getUserRoleSet()) {
            grantList.add(new SimpleGrantedAuthority(userRole.getAppRole().getName()));
        }
        UserDetails userDetails = new JwtResponseUserDetails(
                appUser.getUserName(),
                appUser.getPassword(),
                grantList);
        return userDetails;
    }

    @Override
    public AppUser findByUsername(String userName) {
        return appUserRepository.findAppUserByName(userName);
    }

    @Override
    public Boolean createNewAppUser(AppUser appUser, String roleAdmin) {
        AppUser currentAppUser = appUserRepository.findAppUserByName(appUser.getUserName());
        if (currentAppUser == null) {
            Integer amount = appUserRepository.addNewAppUser(appUser);
            Long roleId = appUserRepository.findAppRoleIdByName(roleAdmin);
            AppUser appUserAfterCreate = appUserRepository.findAppUserByName(appUser.getUserName());
            appUserRepository.addRoleForCustomer(appUserAfterCreate.getId(), roleId);
            return amount > 0;
        }
        return false;
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser findUserById(Long idUser) {
        return appUserRepository.findById(idUser).orElse(null);
    }
}

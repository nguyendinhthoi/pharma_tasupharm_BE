package com.example.pharma_tasupharm_be.dto.user;



import com.example.pharma_tasupharm_be.common.user.ValidateAppUser;
import com.example.pharma_tasupharm_be.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto implements Validator {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private Set<UserRole> userRoleSet;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserDto appUserDto = (AppUserDto) target;
        ValidateAppUser.checkValidateAppUserName(appUserDto.getUserName(), errors);
        ValidateAppUser.checkValidateAppUserPassword(appUserDto.getPassword(), errors);
        ValidateAppUser.checkValidateAppUserEmail(appUserDto.getEmail(),errors);
    }
}
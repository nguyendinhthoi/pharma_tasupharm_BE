package com.example.pharma_tasupharm_be.dto.user;

import com.example.pharma_tasupharm_be.model.user.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto implements Validator {
    private Long id;
    private String userName;
    private String password;
    private Boolean flagDeleted;
    private Boolean flagOnline;
    private String employeeName;
    private String email;
    private String employeeCode;
    private String employeeAddress;
    private String employeePhone;
    private String employeeGender;

    @Lob
    private String employeeImage;
    private String employeeIdCard;
    private java.sql.Date employeeBirthday;
    private java.sql.Date employeeStartDate;

    private static final int OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String EMAIL_DTO = "emailEmployee";
    private static final String NAME_DTO = "nameEmployee";
    private static final String ADDRESS_DTO = "addressEmployee";
    private static final String PHONE_NUMBER_DTO = "phoneEmployee";
    private static final String BIRTHDAY_DTO = "birthdayEmployee";

    @Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;

    @JsonBackReference
    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER)
    private Set<UserRole> userRoleSet;

    public boolean isOTPRequired() {
        if (this.getOneTimePassword() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

        // OTP expires
        return otpRequestedTimeInMillis + OTP_VALID_DURATION >= currentTimeInMillis;
    }

    public UserInfoDto(Long id, String userName, String password, Boolean flagDeleted, Boolean flagOnline, Set<UserRole> userRoleSet) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.flagDeleted = flagDeleted;
        this.flagOnline = flagOnline;
        this.userRoleSet = userRoleSet;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Date getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Date otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public Boolean getFlagOnline() {
        return flagOnline;
    }

    public void setFlagOnline(Boolean flagOnline) {
        this.flagOnline = flagOnline;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInfoDto userInfoDto = (UserInfoDto) target;

        if (userInfoDto.getEmployeeName() == null) {
            errors.rejectValue(NAME_DTO, "", "Vui lòng nhập tên");
        } else if (userInfoDto.getEmployeeName().length() > 100) {
            errors.rejectValue(NAME_DTO, "", "Quá ký tự cho phép");
        } else if (!userInfoDto.getEmployeeName().matches("^[\\p{L}\\s]+$")) {
            errors.rejectValue(NAME_DTO, "", "Tên chỉ chứa định dạng chữ");
        }

        if (userInfoDto.getEmployeeAddress() == null) {
            errors.rejectValue(ADDRESS_DTO, "", "Vui lòng nhập địa chỉ");
        } else if (userInfoDto.getEmployeeAddress().length() > 100) {
            errors.rejectValue(ADDRESS_DTO, "", "Địa chỉ quá ký tự cho phép");
        }

        if (userInfoDto.getEmployeePhone() == null) {
            errors.rejectValue(PHONE_NUMBER_DTO, "", "Vui lòng nhập số điện thoại");
        } else if (userInfoDto.getEmployeePhone().length() > 11) {
            errors.rejectValue(PHONE_NUMBER_DTO, "", "Vui lòng chỉ nhập từ 10 đến 11 số");
        } else if (userInfoDto.getEmployeePhone().length() < 10) {
            errors.rejectValue(PHONE_NUMBER_DTO, "", "Vui lòng chỉ nhập từ 10 đến 11 số");
        } else if (!userInfoDto.getEmployeePhone().matches("^0\\d{9,10}$")) {
            errors.rejectValue(PHONE_NUMBER_DTO, "", "Sai định dạng");
        }
        if (userInfoDto.getEmail() == null) {
            errors.rejectValue(EMAIL_DTO, "", "Vui lòng bổ sung email khách hàng");
        } else if (userInfoDto.getEmail().trim().equals("")) {
            errors.rejectValue(EMAIL_DTO, "", "Không được để trống email");
        } else if (userInfoDto.getEmail().length() < 12) {
            errors.rejectValue(EMAIL_DTO, "", "Email không đủ ký tự cho phép");
        } else if (userInfoDto.getEmail().length() > 50) {
            errors.rejectValue(EMAIL_DTO, "", "Email vượt quá ký tự cho phép");
        } else if (!userInfoDto.getEmail().matches(EMAIL_REGEX)) {
            errors.rejectValue(EMAIL_DTO, "", "Bạn nhập sai định dạng email!");
        }
    }
}

package com.example.pharma_tasupharm_be.common.user;

import org.springframework.validation.Errors;

public class ValidateAppUser {
    private ValidateAppUser() {
        throw new IllegalStateException("Utility class");
    }
    private static final String NAME_NOT_EMPTY = "Không để trống tài khoản";
    private static final String PASSWORD_NOT_EMPTY = "Không để trống mật khẩu";
    private static final String CHAR_LENGTH_LESS_THREE = "Số lượng ký tự phải lớn hơn hoặc bằng 3";
    private static final String CHAR_LENGTH_GREATER_FIFTY = "Số lượng ký tự bé hơn hoặc bằng 50";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String EMAIL_REGEX  = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String CONFIRM_PASSWORD = "confirmPassword";

    public static void checkValidateAppUserName(String name, Errors errors) {
        if (name == null || name.trim().length() == 0) {
            errors.rejectValue(USER_NAME, "", NAME_NOT_EMPTY);
        } else if (name.length() > 50) {
            errors.rejectValue(USER_NAME, "", CHAR_LENGTH_GREATER_FIFTY);
        } else if (name.length() < 3) {
            errors.rejectValue(USER_NAME, "", CHAR_LENGTH_LESS_THREE);
        }
    }

    public static void checkValidateAppUserPassword(String password, Errors errors) {
        if (password == null || password.trim().length() == 0) {
            errors.rejectValue(PASSWORD, "", NAME_NOT_EMPTY);
        } else if (password.length() > 50) {
            errors.rejectValue(PASSWORD, "", CHAR_LENGTH_GREATER_FIFTY);
        } else if (password.length() < 3) {
            errors.rejectValue(PASSWORD, "", CHAR_LENGTH_LESS_THREE);
        }
    }
    public static void checkValidateAppUserEmail(String email, Errors errors) {
        if (email == null) {
            errors.rejectValue(EMAIL, "", "Vui lòng bổ sung email khách hàng");
        } else if (email.trim().equals("")) {
            errors.rejectValue(EMAIL, "", "Không được để trống email");
        }else if (email.length() < 12) {
            errors.rejectValue(EMAIL, "", "Email không đủ ký tự cho phép");
        }
        else if (email.length() > 50) {
            errors.rejectValue(EMAIL, "", "Email vượt quá ký tự cho phép");
        } else if (!email.matches(EMAIL_REGEX)) {
            errors.rejectValue(EMAIL, "", "Bạn nhập sai định dạng email!");
        }
    }
}
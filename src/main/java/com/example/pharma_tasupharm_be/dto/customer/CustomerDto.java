package com.example.pharma_tasupharm_be.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Validator {
    private Long id;
    private String name;
    private String address;
    private String birthday;
    private String email;
    private Integer gender;
    private String phoneNumber;
    private Long userId;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;
        final String Name = "name";
        if (customerDto.getName() == null) {
            errors.rejectValue(Name, "", "Vui lòng nhập tên");
        } else if (customerDto.getName().length() > 100) {
            errors.rejectValue(Name, "", "Quá ký tự cho phép");
        } else if (!customerDto.getName().matches("^[\\p{L}\\s]+$")) {
            errors.rejectValue(Name, "", "Tên chỉ chứa định dạng chữ");
        }
        final String ADDRESS = "address";
        if (customerDto.getAddress() == null) {
            errors.rejectValue(ADDRESS, "", "Vui lòng nhập địa chỉ");
        } else if (customerDto.getAddress().length() > 100) {
            errors.rejectValue(ADDRESS, "", "Địa chỉ quá ký tự cho phép");
        }
        final String PHONE_NUMBER= "phoneNumber";
        if (customerDto.getPhoneNumber() == null) {
            errors.rejectValue(PHONE_NUMBER, "", "Vui lòng nhập số điện thoại");
        } else if (customerDto.getPhoneNumber().length() > 11) {
            errors.rejectValue(PHONE_NUMBER, "", "Vui lòng chỉ nhập từ 10 đến 11 số");
        } else if (customerDto.getPhoneNumber().length() <10) {
            errors.rejectValue(PHONE_NUMBER, "", "Vui lòng chỉ nhập từ 10 đến 11 số");
        } else if (!customerDto.getPhoneNumber().matches("^0\\d{9,10}$")) {
            errors.rejectValue(PHONE_NUMBER, "", "Sai định dạng");
        }
    }
}

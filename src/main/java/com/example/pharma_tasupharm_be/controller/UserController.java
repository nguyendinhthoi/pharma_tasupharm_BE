package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.config.JwtTokenUtil;
import com.example.pharma_tasupharm_be.dto.AppUserDto;
import com.example.pharma_tasupharm_be.dto.AppUserLoginDto;
import com.example.pharma_tasupharm_be.model.customer.Customer;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.model.user.JwtResponse;
import com.example.pharma_tasupharm_be.service.customer.ICustomerService;
import com.example.pharma_tasupharm_be.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICustomerService customerService;
    private static final String LOGIN_FAILED = "Đăng nhập thất bại";


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AppUserLoginDto appUserDto, BindingResult bindingResult) {
        new AppUserLoginDto().validate(appUserDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(LOGIN_FAILED, HttpStatus.UNAUTHORIZED);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUserDto.getUserName(), appUserDto.getPassword()));
        } catch (DisabledException e) {
            return new ResponseEntity<>("Tài khoản của bạn đã bị vô hiệu hóa", HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(LOGIN_FAILED, HttpStatus.UNAUTHORIZED);
        }
        AppUser appUser = appUserService.findByUsername(appUserDto.getUserName());
        UserDetails userDetails = appUserService.loadUserByUsername(appUser.getUserName());

        String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new JwtResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AppUserDto appUserDto, BindingResult bindingResult) {
        new AppUserDto().validate(appUserDto, bindingResult);
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream()
                    .map(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
        }
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserDto.getUserName());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        appUser.setEmail(appUserDto.getEmail());
        Boolean checkAddNewAppUser = appUserService.createNewAppUser(appUser, "ROLE_ADMIN");
        if (!Boolean.TRUE.equals(checkAddNewAppUser)) {
            return new ResponseEntity<>("Đăng kí thất bại, vui lòng chờ trong giây lát",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Thêm mới thành công", HttpStatus.OK);
    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<Object> getUser(@PathVariable String name) {
        AppUser appUser = appUserService.findByUsername(name);
        if (appUser != null) {
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tìm thấy user", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCustomer/{idUser}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long idUser) {
        Customer customer = customerService.findCustomerByIdUser(idUser);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Không tìm thấy", HttpStatus.NOT_FOUND);
        }
    }
}

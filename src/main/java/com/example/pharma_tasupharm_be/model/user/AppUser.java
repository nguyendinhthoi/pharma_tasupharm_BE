package com.example.pharma_tasupharm_be.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String customerName;
    @Column(columnDefinition = "date")
    private String birthday;
    private String email;
    private String address;
    private Integer gender;
    private String phoneNumber;
    @JsonBackReference
    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER)
    private Set<UserRole> userRoleSet;
    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }
}

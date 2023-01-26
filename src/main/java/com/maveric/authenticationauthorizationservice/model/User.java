package com.maveric.authenticationauthorizationservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.maveric.authenticationauthorizationservice.constant.Gender;
import lombok.Data;

import java.util.Collection;

@Data
    public class User implements UserDetails {
        private  String id;

        private String firstName;

        private String middleName;

        private String lastName;

        private String email;

        private String phoneNumber;

        private String address;

        private String dateOfBirth;

        private Gender gender;

        private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
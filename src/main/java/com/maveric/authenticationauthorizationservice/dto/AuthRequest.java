package com.maveric.authenticationauthorizationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class AuthRequest {
    @Email
    private String email;

    @Min(3)
    private String password;
}

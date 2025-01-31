package com.javacode.learn.dto;

import com.javacode.learn.security.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterUserDto {
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}

package com.javacode.learn.utils;

import com.javacode.learn.dto.RegisterUserDto;
import com.javacode.learn.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoMapper {
    public static User mapToEntity(RegisterUserDto dto, PasswordEncoder encoder) {
        return User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .isAccountNonLocked(true)
                .failedLoginAttempts(0)
                .build();
    }
}

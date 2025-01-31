package com.javacode.learn.service.impl;

import com.javacode.learn.dto.LoginUserDto;
import com.javacode.learn.dto.RegisterUserDto;
import com.javacode.learn.entity.User;
import com.javacode.learn.repository.UserRepository;
import com.javacode.learn.security.UserDetailedService;
import com.javacode.learn.service.UserRegisterService;
import com.javacode.learn.utils.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailedService userDetailedService;

    @Override
    public void createUser(RegisterUserDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new RuntimeException("Already exist");
        } else {
            userRepository.save(UserDtoMapper.mapToEntity(registerDto, passwordEncoder));
        }
    }

    @Override
    public User login(LoginUserDto loginUserDto) {
        User user = userRepository.findByUsername(loginUserDto.getUsername()).orElseThrow(() ->
                new RuntimeException("Not found"));
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {
            userDetailedService.increaseFailedLoginAttempts(user);
            throw new RuntimeException("incorrect password");
        } else {
            return user;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Not found"));
    }
}

package com.javacode.learn.service;

import com.javacode.learn.dto.LoginUserDto;
import com.javacode.learn.dto.RegisterUserDto;
import com.javacode.learn.entity.User;

public interface UserRegisterService {
    void createUser(RegisterUserDto registerDto);

    User login(LoginUserDto loginUserDto);

    void deleteUser(Long id);


}

package com.javacode.learn.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacode.learn.dto.LoginUserDto;
import com.javacode.learn.dto.RegisterUserDto;
import com.javacode.learn.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterServiceImplTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private RegisterUserDto registerUserDto;
    private LoginUserDto loginUserDto;

    @BeforeEach
    void setUp() {
        registerUserDto = RegisterUserDto.builder()
                .username("name1")
                .password("password1")
                .role(Role.SUPER_ADMIN)
                .build();

        loginUserDto = LoginUserDto.builder()
                .username("name1")
                .password("password1")
                .build();
    }

    @Test
    //@WithMockUser
    void createUser() throws Exception {
        String json = objectMapper.writeValueAsString(registerUserDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/app/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //.with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void login() {
    }
}
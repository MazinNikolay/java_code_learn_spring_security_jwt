package com.javacode.learn.controller;

import com.javacode.learn.dto.LoginUserDto;
import com.javacode.learn.dto.RegisterUserDto;
import com.javacode.learn.entity.User;
import com.javacode.learn.security.UserDetailedService;
import com.javacode.learn.service.UserRegisterService;
import com.javacode.learn.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailedService ourUserDetailedService;
    private final UserRegisterService userRegisterService;
    private final JwtUtils jwtUtils;

    @GetMapping("/profile")
    // @PreAuthorize("hasRole('USER')")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok("User profile");
    }

    @GetMapping("/moderator")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> moderateContent() {
        return ResponseEntity.ok("Moderator access");
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Admin access");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto dto) {
        userRegisterService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDto dto) {
        //return ResponseEntity.ok().body(userRegisterService.login(dto));
        String token = jwtUtils.generateToken(userRegisterService.login(dto));
        return ResponseEntity.ok().body(token);
    }
}

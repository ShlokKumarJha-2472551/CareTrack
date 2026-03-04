package com.cts.authservice.controller;

import com.cts.authservice.dto.*;
import com.cts.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(){
        return authService.getCurrentUser();
    }

}

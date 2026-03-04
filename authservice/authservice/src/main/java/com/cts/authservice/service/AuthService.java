package com.cts.authservice.service;

import com.cts.authservice.dto.*;

public interface AuthService {
    RegisterResponse register(RegisterRequest  request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser();
}

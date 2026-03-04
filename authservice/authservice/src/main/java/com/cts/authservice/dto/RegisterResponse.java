package com.cts.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private Long userId;
    private String email;
    private String role;
    private String message;

}

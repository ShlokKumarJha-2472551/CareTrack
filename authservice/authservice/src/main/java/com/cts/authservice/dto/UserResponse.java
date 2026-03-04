package com.cts.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private Set<String> roles;
}

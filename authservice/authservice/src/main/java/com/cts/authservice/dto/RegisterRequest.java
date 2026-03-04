package com.cts.authservice.dto;

import com.cts.authservice.enums.RoleType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;


    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password must be at-least 6 characters")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$",message = "Phone number must be 10 digits")
    private String phone;

    @NotNull(message = "Role is required")
    private RoleType role;

    private ProviderRequest provider;
}

package com.cts.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ProviderRequest {
    @NotBlank(message = "Speciality is required")
    private String speciality;

    @NotBlank(message = "Licence is required")
    private String licenseNumber;
    @NotBlank(message = "Department is required")
    private String department;

}

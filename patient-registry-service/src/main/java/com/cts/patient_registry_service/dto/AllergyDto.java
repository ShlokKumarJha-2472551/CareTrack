package com.cts.patient_registry_service.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
public class AllergyDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        private Long patientId;
        @NotBlank(message = "Substance is required")
        private String substance;
        private String reaction;
        private String severity;
        private LocalDate notedDate;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long allergyId;
        private Long patientId;
        private String substance;
        private String reaction;
        private String severity;
        private LocalDate notedDate;
    }
}
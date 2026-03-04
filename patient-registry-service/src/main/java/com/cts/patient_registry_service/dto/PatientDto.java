package com.cts.patient_registry_service.dto;

import com.cts.patient_registry_service.entity.Patient;

import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.List;

public class PatientDto {

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class CreateRequest {

        @NotBlank(message = "MRN is required")

        private String mrn;

        @NotBlank(message = "Name is required")

        private String name;

        @NotNull(message = "Date of birth is required")

        @Past(message = "DOB must be in the past")

        private LocalDate dob;

        @NotBlank(message = "Gender is required")

        private String gender;

        private String contactInfo;

        private Long primaryProviderId;

        @NotNull(message = "Status is required")

        private Patient.PatientStatus status;

    }

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class UpdateRequest {

        private String name;

        private LocalDate dob;

        private String gender;

        private String contactInfo;

        private Long primaryProviderId;

        private Patient.PatientStatus status;

    }

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class Response {

        private Long patientId;

        private String mrn;

        private String name;

        private LocalDate dob;

        private String gender;

        private String contactInfo;

        private Long primaryProviderId;

        private Patient.PatientStatus status;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        private List<AllergyDto.Response> allergies;

        private List<ProblemDto.Response> problems;

    }

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class Summary {

        private Long patientId;

        private String mrn;

        private String name;

        private LocalDate dob;

        private String gender;

        private Patient.PatientStatus status;

    }

}

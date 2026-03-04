package com.cts.patient_registry_service.dto;

import com.cts.patient_registry_service.entity.Problem;

import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;

public class ProblemDto {

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class CreateRequest {

        private Long patientId;

        @NotBlank(message = "Code is required")

        private String code;

        private String description;

        private LocalDate onsetDate;

        @NotNull(message = "Status is required")

        private Problem.ProblemStatus status;

    }

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class UpdateRequest {

        private String description;

        private LocalDate onsetDate;

        private Problem.ProblemStatus status;

    }

    @Data

    @NoArgsConstructor

    @AllArgsConstructor

    @Builder

    public static class Response {

        private Long problemId;

        private Long patientId;

        private String code;

        private String description;

        private LocalDate onsetDate;

        private Problem.ProblemStatus status;

    }

}

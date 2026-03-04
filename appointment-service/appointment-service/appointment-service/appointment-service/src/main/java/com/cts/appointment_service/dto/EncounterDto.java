package com.cts.appointment_service.dto;

import com.cts.appointment_service.entity.Encounter.EncounterType;
import com.cts.appointment_service.entity.Encounter.EncounterStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

public class EncounterDto {

    @Data
    public static class CreateRequest {
        @NotNull(message = "Patient ID is required")
        private Long patientId;

        @NotNull(message = "Provider ID is required")
        private Long providerId;

        @NotNull(message = "Encounter date time is required")
        private LocalDateTime encounterDateTime;

        @NotNull(message = "Type is required")
        private EncounterType type;

        private String diagnosisCode;
        private String summary;
    }

    @Data
    public static class UpdateRequest {
        private String diagnosisCode;
        private String summary;
        private EncounterStatus status;
    }

    @Data
    public static class Response {
        private Long encounterId;
        private Long patientId;
        private Long providerId;
        private LocalDateTime encounterDateTime;
        private EncounterType type;
        private String diagnosisCode;
        private String summary;
        private EncounterStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}

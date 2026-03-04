package com.cts.appointment_service.dto;

import com.cts.appointment_service.entity.Appointment.AppointmentStatus;
import com.cts.appointment_service.entity.Encounter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

public class AppointmentDto {

    @Data
    public static class CreateRequest {
        @NotNull(message = "Patient ID is required")
        private Long patientId;

        @NotNull(message = "Provider ID is required")
        private Long providerId;

        private AppointmentStatus status;

        @NotNull(message = "Scheduled date time is required")
        private LocalDateTime scheduledDateTime;

        private String reason;
    }

    @Data
    public static class UpdateRequest {
        private LocalDateTime scheduledDateTime;
        private String reason;
        private AppointmentStatus status;
    }

    @Data
    public static class Response {
        private Long appointmentId;
        private Long patientId;
        private Long providerId;
        private LocalDateTime scheduledDateTime;
        private String reason;
        private AppointmentStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}

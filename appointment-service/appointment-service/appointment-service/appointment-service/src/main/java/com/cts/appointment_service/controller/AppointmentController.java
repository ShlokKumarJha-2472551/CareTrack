package com.cts.appointment_service.controller;

import com.cts.appointment_service.dto.AppointmentDto;
import com.cts.appointment_service.dto.EncounterDto;
import com.cts.appointment_service.entity.Appointment.AppointmentStatus;
import com.cts.appointment_service.entity.Encounter.EncounterStatus;
import com.cts.appointment_service.entity.Encounter.EncounterType;
import com.cts.appointment_service.service.AppointmentService;
import com.cts.appointment_service.service.EncounterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final EncounterService encounterService;

    // ==================== APPOINTMENT ENDPOINTS ====================

    @PostMapping("/appointments")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<AppointmentDto.Response> createAppointment(
            @Valid @RequestBody AppointmentDto.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createAppointment(request));
    }

    @GetMapping("/appointments/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<AppointmentDto.Response> getAppointmentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id));
    }

    @GetMapping("/appointments")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<List<AppointmentDto.Response>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllAppointments());
    }

    @GetMapping("/appointments/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<List<AppointmentDto.Response>> getByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/appointments/status/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<List<AppointmentDto.Response>> getByStatus(
            @PathVariable AppointmentStatus status) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByStatus(status));
    }

    @PutMapping("/appointments/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<AppointmentDto.Response> updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDto.UpdateRequest request) {
        return ResponseEntity.ok(
                appointmentService.updateAppointment(id, request));
    }

    @PatchMapping("/appointments/{id}/cancel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<Void> cancelAppointment(
            @PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/appointments/{id}/checkin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<Void> checkInAppointment(
            @PathVariable Long id) {
        appointmentService.checkInAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== ENCOUNTER ENDPOINTS ====================

    @PostMapping("/encounters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<EncounterDto.Response> createEncounter(
            @Valid @RequestBody EncounterDto.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(encounterService.createEncounter(request));
    }

    @GetMapping("/encounters/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<EncounterDto.Response> getEncounterById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                encounterService.getEncounterById(id));
    }

    @GetMapping("/encounters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<List<EncounterDto.Response>> getAllEncounters() {
        return ResponseEntity.ok(
                encounterService.getAllEncounters());
    }

    @GetMapping("/encounters/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN','ROLE_PATIENT')")
    public ResponseEntity<List<EncounterDto.Response>> getEncountersByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
                encounterService.getEncountersByPatient(patientId));
    }

    @GetMapping("/encounters/type/{type}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<List<EncounterDto.Response>> getEncountersByType(
            @PathVariable EncounterType type) {
        return ResponseEntity.ok(
                encounterService.getEncountersByType(type));
    }

    @GetMapping("/encounters/status/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<List<EncounterDto.Response>> getEncountersByStatus(
            @PathVariable EncounterStatus status) {
        return ResponseEntity.ok(
                encounterService.getEncountersByStatus(status));
    }

    @PutMapping("/encounters/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<EncounterDto.Response> updateEncounter(
            @PathVariable Long id,
            @RequestBody EncounterDto.UpdateRequest request) {
        return ResponseEntity.ok(
                encounterService.updateEncounter(id, request));
    }

    @PatchMapping("/encounters/{id}/close")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLINICIAN')")
    public ResponseEntity<Void> closeEncounter(
            @PathVariable Long id) {
        encounterService.closeEncounter(id);
        return ResponseEntity.noContent().build();
    }
}

package com.cts.patient_registry_service.controller;

import com.cts.patient_registry_service.dto.AllergyDto;

import com.cts.patient_registry_service.dto.PatientDto;

import com.cts.patient_registry_service.dto.ProblemDto;

import com.cts.patient_registry_service.entity.Patient;

import com.cts.patient_registry_service.service.AllergyService;

import com.cts.patient_registry_service.service.PatientService;

import com.cts.patient_registry_service.service.ProblemService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/patients")

@RequiredArgsConstructor

public class PatientController {

    private final PatientService patientService;

    private final AllergyService allergyService;

    private final ProblemService problemService;

    // ── Patient Endpoints ────────────────────────────────

    @PostMapping

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN')")

    public ResponseEntity<PatientDto.Response> createPatient(

            @Valid @RequestBody PatientDto.CreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(patientService.createPatient(request));

    }

    @GetMapping("/{patientId}")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN')")

    public ResponseEntity<PatientDto.Response> getPatientById(

            @PathVariable Long patientId) {

        return ResponseEntity.ok(patientService.getPatientById(patientId));

    }

    @GetMapping("/mrn/{mrn}")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN')")

    public ResponseEntity<PatientDto.Response> getPatientByMrn(

            @PathVariable String mrn) {

        return ResponseEntity.ok(patientService.getPatientByMrn(mrn));

    }

    @PutMapping("/{patientId}")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN')")

    public ResponseEntity<PatientDto.Response> updatePatient(

            @PathVariable Long patientId,

            @Valid @RequestBody PatientDto.UpdateRequest request) {

        return ResponseEntity.ok(patientService.updatePatient(patientId, request));

    }

    @PatchMapping("/{patientId}/deactivate")

    @PreAuthorize("hasRole('CLINICIAN')")

    public ResponseEntity<Void> deactivatePatient(

            @PathVariable Long patientId) {

        patientService.deactivatePatient(patientId);

        return ResponseEntity.noContent().build();

    }

    @GetMapping

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN') or hasRole('NURSE')")

    public ResponseEntity<List<PatientDto.Summary>> getAllPatients(

            @RequestParam(required = false) Patient.PatientStatus status) {

        if (status != null) {

            return ResponseEntity.ok(patientService.getPatientsByStatus(status));

        }

        return ResponseEntity.ok(patientService.getAllPatients());

    }

    @GetMapping("/search")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('ADMIN')")

    public ResponseEntity<List<PatientDto.Summary>> searchPatients(

            @RequestParam String keyword) {

        return ResponseEntity.ok(patientService.searchPatients(keyword));

    }

    // ── Allergy Endpoints ────────────────────────────────

    @PostMapping("/{patientId}/allergies")

    @PreAuthorize("hasRole('CLINICIAN')")

    public ResponseEntity<AllergyDto.Response> addAllergy(

            @PathVariable Long patientId,

            @Valid @RequestBody AllergyDto.CreateRequest request) {

        request.setPatientId(patientId);

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(allergyService.addAllergy(request));

    }

    @GetMapping("/{patientId}/allergies")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('NURSE')")

    public ResponseEntity<List<AllergyDto.Response>> getAllergies(

            @PathVariable Long patientId) {

        return ResponseEntity.ok(allergyService.getAllergiesByPatient(patientId));

    }

    @DeleteMapping("/{patientId}/allergies/{allergyId}")

    @PreAuthorize("hasRole('CLINICIAN')")

    public ResponseEntity<Void> deleteAllergy(

            @PathVariable Long patientId,

            @PathVariable Long allergyId) {

        allergyService.deleteAllergy(allergyId);

        return ResponseEntity.noContent().build();

    }

    // ── Problem Endpoints ────────────────────────────────

    @PostMapping("/{patientId}/problems")

    @PreAuthorize("hasRole('CLINICIAN')")

    public ResponseEntity<ProblemDto.Response> addProblem(

            @PathVariable Long patientId,

            @Valid @RequestBody ProblemDto.CreateRequest request) {

        request.setPatientId(patientId);

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(problemService.addProblem(request));

    }

    @GetMapping("/{patientId}/problems")

    @PreAuthorize("hasRole('CLINICIAN') or hasRole('NURSE')")

    public ResponseEntity<List<ProblemDto.Response>> getProblems(

            @PathVariable Long patientId) {

        return ResponseEntity.ok(problemService.getProblemsByPatient(patientId));

    }

    @PutMapping("/{patientId}/problems/{problemId}")

    @PreAuthorize("hasRole('CLINICIAN')")

    public ResponseEntity<ProblemDto.Response> updateProblem(

            @PathVariable Long patientId,

            @PathVariable Long problemId,

            @Valid @RequestBody ProblemDto.UpdateRequest request) {

        return ResponseEntity.ok(problemService.updateProblem(problemId, request));

    }

}

package com.cts.patient_registry_service.service;

import com.cts.patient_registry_service.dto.AllergyDto;

import com.cts.patient_registry_service.dto.PatientDto;

import com.cts.patient_registry_service.dto.ProblemDto;

import com.cts.patient_registry_service.entity.Patient;

import com.cts.patient_registry_service.exception.DuplicateResourceException;

import com.cts.patient_registry_service.exception.ResourceNotFoundException;

import com.cts.patient_registry_service.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor

@Slf4j

@Transactional

public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override

    public PatientDto.Response createPatient(

            PatientDto.CreateRequest request) {

        if (patientRepository.existsByMrn(request.getMrn())) {

            throw new DuplicateResourceException(

                    "Patient with MRN " + request.getMrn()

                            + " already exists.");

        }

        Patient patient = Patient.builder()

                .mrn(request.getMrn())

                .name(request.getName())

                .dob(request.getDob())

                .gender(request.getGender())

                .contactInfo(request.getContactInfo())

                .primaryProviderId(request.getPrimaryProviderId())

                .status(request.getStatus())

                .build();

        return mapToResponse(patientRepository.save(patient));

    }

    @Override

    @Transactional(readOnly = true)

    public PatientDto.Response getPatientById(Long patientId) {

        Patient patient = patientRepository

                .findById(patientId)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Patient not found with ID: "

                                + patientId));

        return mapToResponse(patient);

    }

    @Override

    @Transactional(readOnly = true)

    public PatientDto.Response getPatientByMrn(String mrn) {

        Patient patient = patientRepository

                .findByMrn(mrn)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Patient not found with MRN: " + mrn));

        return mapToResponse(patient);

    }

    @Override

    public PatientDto.Response updatePatient(

            Long patientId,

            PatientDto.UpdateRequest request) {

        Patient patient = patientRepository

                .findById(patientId)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Patient not found with ID: "

                                + patientId));

        if (request.getName() != null)

            patient.setName(request.getName());

        if (request.getDob() != null)

            patient.setDob(request.getDob());

        if (request.getGender() != null)

            patient.setGender(request.getGender());

        if (request.getContactInfo() != null)

            patient.setContactInfo(request.getContactInfo());

        if (request.getPrimaryProviderId() != null)

            patient.setPrimaryProviderId(

                    request.getPrimaryProviderId());

        if (request.getStatus() != null)

            patient.setStatus(request.getStatus());

        return mapToResponse(patientRepository.save(patient));

    }

    @Override

    public void deactivatePatient(Long patientId) {

        Patient patient = patientRepository

                .findById(patientId)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Patient not found with ID: "

                                + patientId));

        patient.setStatus(Patient.PatientStatus.INACTIVE);

        patientRepository.save(patient);

    }

    @Override

    @Transactional(readOnly = true)

    public List<PatientDto.Summary> getAllPatients() {

        return patientRepository.findAll()

                .stream()

                .map(this::mapToSummary)

                .collect(Collectors.toList());

    }

    @Override

    @Transactional(readOnly = true)

    public List<PatientDto.Summary> getPatientsByStatus(

            Patient.PatientStatus status) {

        return patientRepository.findByStatus(status)

                .stream()

                .map(this::mapToSummary)

                .collect(Collectors.toList());

    }

    @Override

    @Transactional(readOnly = true)

    public List<PatientDto.Summary> searchPatients(

            String keyword) {

        return patientRepository

                .findByNameContainingIgnoreCase(keyword)

                .stream()

                .map(this::mapToSummary)

                .collect(Collectors.toList());

    }

    private PatientDto.Response mapToResponse(Patient p) {

        return PatientDto.Response.builder()

                .patientId(p.getPatientId())

                .mrn(p.getMrn())

                .name(p.getName())

                .dob(p.getDob())

                .gender(p.getGender())

                .contactInfo(p.getContactInfo())

                .primaryProviderId(p.getPrimaryProviderId())

                .status(p.getStatus())

                .createdAt(p.getCreatedAt())

                .updatedAt(p.getUpdatedAt())

                .allergies(List.of())

                .problems(List.of())

                .build();

    }

    private PatientDto.Summary mapToSummary(Patient p) {

        return PatientDto.Summary.builder()

                .patientId(p.getPatientId())

                .mrn(p.getMrn())

                .name(p.getName())

                .dob(p.getDob())

                .gender(p.getGender())

                .status(p.getStatus())

                .build();

    }

}

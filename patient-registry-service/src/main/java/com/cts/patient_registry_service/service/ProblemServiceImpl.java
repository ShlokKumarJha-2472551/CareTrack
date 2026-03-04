package com.cts.patient_registry_service.service;

import com.cts.patient_registry_service.dto.ProblemDto;

import com.cts.patient_registry_service.entity.Patient;

import com.cts.patient_registry_service.entity.Problem;

import com.cts.patient_registry_service.exception.ResourceNotFoundException;

import com.cts.patient_registry_service.repository.PatientRepository;

import com.cts.patient_registry_service.repository.ProblemRepository;

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

public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    private final PatientRepository patientRepository;

    @Override

    public ProblemDto.Response addProblem(

            ProblemDto.CreateRequest request) {

        Patient patient = patientRepository

                .findById(request.getPatientId())

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Patient not found with ID: "

                                + request.getPatientId()));

        Problem problem = Problem.builder()

                .patient(patient)

                .code(request.getCode())

                .description(request.getDescription())

                .onsetDate(request.getOnsetDate())

                .status(request.getStatus())

                .build();

        return mapToResponse(problemRepository.save(problem));

    }

    @Override

    @Transactional(readOnly = true)

    public List<ProblemDto.Response> getProblemsByPatient(

            Long patientId) {

        if (!patientRepository.existsById(patientId))

            throw new ResourceNotFoundException(

                    "Patient not found with ID: " + patientId);

        return problemRepository

                .findByPatientPatientId(patientId)

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());

    }

    @Override

    public ProblemDto.Response updateProblem(

            Long problemId,

            ProblemDto.UpdateRequest request) {

        Problem problem = problemRepository

                .findById(problemId)

                .orElseThrow(() -> new ResourceNotFoundException(

                        "Problem not found with ID: "

                                + problemId));

        if (request.getDescription() != null)

            problem.setDescription(request.getDescription());

        if (request.getOnsetDate() != null)

            problem.setOnsetDate(request.getOnsetDate());

        if (request.getStatus() != null)

            problem.setStatus(request.getStatus());

        return mapToResponse(problemRepository.save(problem));

    }

    private ProblemDto.Response mapToResponse(Problem pr) {

        return ProblemDto.Response.builder()

                .problemId(pr.getProblemId())

                .patientId(pr.getPatient().getPatientId())

                .code(pr.getCode())

                .description(pr.getDescription())

                .onsetDate(pr.getOnsetDate())

                .status(pr.getStatus())

                .build();

    }

}

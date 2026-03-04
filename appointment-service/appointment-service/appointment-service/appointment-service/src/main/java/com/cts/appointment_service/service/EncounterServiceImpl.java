package com.cts.appointment_service.service;

import com.cts.appointment_service.dto.EncounterDto;
import com.cts.appointment_service.entity.Encounter;
import com.cts.appointment_service.entity.Encounter.EncounterStatus;
import com.cts.appointment_service.entity.Encounter.EncounterType;
import com.cts.appointment_service.exception.ResourceNotFoundException;
import com.cts.appointment_service.repository.EncounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EncounterServiceImpl implements EncounterService {

    private final EncounterRepository encounterRepository;

    @Override
    public EncounterDto.Response createEncounter(
            EncounterDto.CreateRequest request) {
        Encounter encounter = Encounter.builder()
                .patientId(request.getPatientId())
                .providerId(request.getProviderId())
                .encounterDateTime(request.getEncounterDateTime())
                .type(request.getType())
                .diagnosisCode(request.getDiagnosisCode())
                .summary(request.getSummary())
                .status(EncounterStatus.OPEN)
                .build();
        return mapToResponse(encounterRepository.save(encounter));
    }

    @Override
    public EncounterDto.Response getEncounterById(Long id) {
        return mapToResponse(findById(id));
    }

    @Override
    public List<EncounterDto.Response> getAllEncounters() {
        return encounterRepository.findAll()
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EncounterDto.Response> getEncountersByPatient(
            Long patientId) {
        return encounterRepository.findByPatientId(patientId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EncounterDto.Response> getEncountersByType(
            EncounterType type) {
        return encounterRepository.findByType(type)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EncounterDto.Response> getEncountersByStatus(
            EncounterStatus status) {
        return encounterRepository.findByStatus(status)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EncounterDto.Response updateEncounter(
            Long id, EncounterDto.UpdateRequest request) {
        Encounter encounter = findById(id);
        if (request.getDiagnosisCode() != null)
            encounter.setDiagnosisCode(request.getDiagnosisCode());
        if (request.getSummary() != null)
            encounter.setSummary(request.getSummary());
        if (request.getStatus() != null)
            encounter.setStatus(request.getStatus());
        return mapToResponse(encounterRepository.save(encounter));
    }

    @Override
    public void closeEncounter(Long id) {
        Encounter encounter = findById(id);
        encounter.setStatus(EncounterStatus.CLOSED);
        encounterRepository.save(encounter);
    }

    private Encounter findById(Long id) {
        return encounterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Encounter not found with id: " + id));
    }

    private EncounterDto.Response mapToResponse(Encounter e) {
        EncounterDto.Response response = new EncounterDto.Response();
        response.setEncounterId(e.getEncounterId());
        response.setPatientId(e.getPatientId());
        response.setProviderId(e.getProviderId());
        response.setEncounterDateTime(e.getEncounterDateTime());
        response.setType(e.getType());
        response.setDiagnosisCode(e.getDiagnosisCode());
        response.setSummary(e.getSummary());
        response.setStatus(e.getStatus());
        response.setCreatedAt(e.getCreatedAt());
        response.setUpdatedAt(e.getUpdatedAt());
        return response;
    }
}

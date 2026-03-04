package com.cts.patient_registry_service.service;
import com.cts.patient_registry_service.dto.AllergyDto;
import com.cts.patient_registry_service.entity.Allergy;
import com.cts.patient_registry_service.entity.Patient;
import com.cts.patient_registry_service.exception.DuplicateResourceException;
import com.cts.patient_registry_service.exception.ResourceNotFoundException;
import com.cts.patient_registry_service.repository.AllergyRepository;
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
public class AllergyServiceImpl implements AllergyService {
    private final AllergyRepository allergyRepository;
    private final PatientRepository patientRepository;
    @Override
    public AllergyDto.Response addAllergy(
            AllergyDto.CreateRequest request) {
        Patient patient = patientRepository
                .findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with ID: "
                                + request.getPatientId()));
        if (allergyRepository
                .existsByPatientPatientIdAndSubstanceIgnoreCase(
                        request.getPatientId(),
                        request.getSubstance())) {
            throw new DuplicateResourceException(
                    "Allergy to '" + request.getSubstance()
                            + "' already recorded.");
        }
        Allergy allergy = Allergy.builder()
                .patient(patient)
                .substance(request.getSubstance())
                .reaction(request.getReaction())
                .severity(request.getSeverity())
                .notedDate(request.getNotedDate())
                .build();
        return mapToResponse(allergyRepository.save(allergy));
    }
    @Override
    @Transactional(readOnly = true)
    public List<AllergyDto.Response> getAllergiesByPatient(
            Long patientId) {
        if (!patientRepository.existsById(patientId))
            throw new ResourceNotFoundException(
                    "Patient not found with ID: " + patientId);
        return allergyRepository
                .findByPatientPatientId(patientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteAllergy(Long allergyId) {
        if (!allergyRepository.existsById(allergyId))
            throw new ResourceNotFoundException(
                    "Allergy not found with ID: " + allergyId);
        allergyRepository.deleteById(allergyId);
    }
    private AllergyDto.Response mapToResponse(Allergy a) {
        return AllergyDto.Response.builder()
                .allergyId(a.getAllergyId())
                .patientId(a.getPatient().getPatientId())
                .substance(a.getSubstance())
                .reaction(a.getReaction())
                .severity(a.getSeverity())
                .notedDate(a.getNotedDate())
                .build();
    }
}
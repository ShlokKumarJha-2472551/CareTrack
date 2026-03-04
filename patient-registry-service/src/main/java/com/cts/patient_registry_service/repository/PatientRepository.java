package com.cts.patient_registry_service.repository;
import com.cts.patient_registry_service.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Long> {
    Optional<Patient> findByMrn(String mrn);
    boolean existsByMrn(String mrn);
    List<Patient> findByStatus(Patient.PatientStatus status);
    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findByPrimaryProviderId(Long providerId);
}
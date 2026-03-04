package com.cts.patient_registry_service.repository;
import com.cts.patient_registry_service.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AllergyRepository
        extends JpaRepository<Allergy, Long> {
    List<Allergy> findByPatientPatientId(Long patientId);
    boolean existsByPatientPatientIdAndSubstanceIgnoreCase(
            Long patientId, String substance);
}
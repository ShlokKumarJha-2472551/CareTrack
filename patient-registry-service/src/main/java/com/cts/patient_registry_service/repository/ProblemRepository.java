package com.cts.patient_registry_service.repository;
import com.cts.patient_registry_service.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProblemRepository
        extends JpaRepository<Problem, Long> {
    List<Problem> findByPatientPatientId(Long patientId);
    List<Problem> findByPatientPatientIdAndStatus(
            Long patientId, Problem.ProblemStatus status);
}
package com.cts.caretrack.clinicaldocumentmanagement.repository;

import com.cts.caretrack.clinicaldocumentmanagement.entity.ClinicalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalDocumentRepository extends JpaRepository<ClinicalDocument,Long> {
    List<ClinicalDocument> findByPatientId(Long patientId);
    List<ClinicalDocument> findByEncounterId(Long encounterId);
}

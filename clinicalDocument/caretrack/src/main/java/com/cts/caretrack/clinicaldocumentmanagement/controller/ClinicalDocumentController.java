package com.cts.caretrack.clinicaldocumentmanagement.controller;


import com.cts.caretrack.clinicaldocumentmanagement.dto.ClinicalDocumentRequest;
import com.cts.caretrack.clinicaldocumentmanagement.dto.ClinicalDocumentResponse;
import com.cts.caretrack.clinicaldocumentmanagement.service.ClinicalDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class ClinicalDocumentController {
    private final ClinicalDocumentService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CLINICIAN')")
    public ResponseEntity<ClinicalDocumentResponse> create(@Valid @RequestBody ClinicalDocumentRequest request){
        return new ResponseEntity<>(service.createDocument(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PATIENT','CLINICIAN','ADMIN')")
    public ResponseEntity<ClinicalDocumentResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(service.getDocument(id));
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('CLINICIAN','ADMIN') or #patientId == authentication.principal")
    public ResponseEntity<List<ClinicalDocumentResponse>> getByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getByPatient(patientId));
    }
    @PutMapping("/{id}/sign")
    @PreAuthorize("hasRole('CLINICIAN')")
    public ResponseEntity<ClinicalDocumentResponse> sign(@PathVariable Long id){
        return ResponseEntity.ok(service.signDocument(id));
    }

    @GetMapping("/encounter/{encounterId}")
    @PreAuthorize("hasAnyRole('CLINICIAN','ADMIN')")
    public ResponseEntity<List<ClinicalDocumentResponse>> getByEncounter(@PathVariable Long encounterId){
        return ResponseEntity.ok(service.getByEncounter(encounterId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteDocument(id);
        return ResponseEntity.ok("Document deleted Successfully.");
    }
}

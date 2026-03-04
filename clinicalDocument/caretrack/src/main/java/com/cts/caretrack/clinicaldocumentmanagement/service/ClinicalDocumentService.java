package com.cts.caretrack.clinicaldocumentmanagement.service;


import com.cts.caretrack.clinicaldocumentmanagement.dto.ClinicalDocumentRequest;
import com.cts.caretrack.clinicaldocumentmanagement.dto.ClinicalDocumentResponse;
import com.cts.caretrack.clinicaldocumentmanagement.entity.ClinicalDocument;
import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentStatus;
import com.cts.caretrack.clinicaldocumentmanagement.exception.ResourceNotFoundException;
import com.cts.caretrack.clinicaldocumentmanagement.repository.ClinicalDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicalDocumentService{
    private final ClinicalDocumentRepository clinicalDocumentRepository;



//    create the document
   public ClinicalDocumentResponse createDocument(ClinicalDocumentRequest request){

       Authentication authentication = SecurityContextHolder.getContext()
               .getAuthentication();

       Long loggedInUserId = (Long) authentication.getPrincipal();

       ClinicalDocument document = new ClinicalDocument();
       document.setEncounterId(request.getEncounterId());
       document.setPatientId(request.getPatientId());
       document.setDocumentType(request.getDocumentType());
       document.setContent(request.getContent());
       document.setAttachmentUri(request.getAttachmentUri());
       document.setAuthorUserId(loggedInUserId);
       document.setStatus(DocumentStatus.DRAFT);

       ClinicalDocument saved = clinicalDocumentRepository.save(document);
       return mapToResponse(saved);

   }
//get by id
   public ClinicalDocumentResponse getDocument(Long id){
       ClinicalDocument document = clinicalDocumentRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Document not found with id: " + id));

       return mapToResponse(document);
   }


//   get by patient
   public List<ClinicalDocumentResponse> getByPatient(Long patientId){
       return clinicalDocumentRepository.findByPatientId(patientId)
               .stream()
               .map(this::mapToResponse)
               .collect(Collectors.toList());
   }


//   get by encounter

   public List<ClinicalDocumentResponse> getByEncounter(Long encounterId){
       return clinicalDocumentRepository.findByEncounterId(encounterId)
               .stream()
               .map(this::mapToResponse)
               .collect(Collectors.toList());
   }


//   sign document

   public ClinicalDocumentResponse signDocument(Long id){
       ClinicalDocument document = clinicalDocumentRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));

       document.setStatus(DocumentStatus.SIGNED);
       document.setSignedDate(LocalDateTime.now());

       ClinicalDocument updated = clinicalDocumentRepository.save(document);

       return mapToResponse(updated);
   }


//   deletedocument
   public void deleteDocument(Long id){
       ClinicalDocument document = clinicalDocumentRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Document not found with id: " + id));

       clinicalDocumentRepository.delete(document);
   }

   private ClinicalDocumentResponse mapToResponse(ClinicalDocument document){
       ClinicalDocumentResponse response = new ClinicalDocumentResponse();
       response.setDocumentId(document.getDocumentId());
       response.setEncounterId(document.getEncounterId());
       response.setPatientId(document.getPatientId());
       response.setDocumentType(document.getDocumentType());
       response.setContent(document.getContent());
       response.setAttachmentUri(document.getAttachmentUri());
       response.setAuthorUserId(document.getAuthorUserId());
       response.setSignedDate(document.getSignedDate());
       response.setStatus(document.getStatus());

       return response;
   }
}

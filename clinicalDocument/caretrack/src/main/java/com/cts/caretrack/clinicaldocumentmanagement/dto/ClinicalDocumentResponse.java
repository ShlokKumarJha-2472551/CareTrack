package com.cts.caretrack.clinicaldocumentmanagement.dto;

import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentStatus;
import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentType;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ClinicalDocumentResponse {

    private Long documentId;
    private Long encounterId;
    private Long patientId;
    private DocumentType documentType;
    private String content;
    private String attachmentUri;
    private Long authorUserId;
    private LocalDateTime signedDate;
    private DocumentStatus status;
}

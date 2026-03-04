package com.cts.caretrack.clinicaldocumentmanagement.dto;

import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClinicalDocumentRequest {

    @NotNull(message = "Encounter ID is required")
    private Long encounterId;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Document Type is required")
    private DocumentType documentType;

    private String content;

    private String attachmentUri;

//    @NotNull(message = "Author User Id is required")
//    private Long authorUserId;
}

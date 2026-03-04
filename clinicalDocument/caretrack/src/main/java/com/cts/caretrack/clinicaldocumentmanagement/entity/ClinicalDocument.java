package com.cts.caretrack.clinicaldocumentmanagement.entity;

import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentStatus;
import com.cts.caretrack.clinicaldocumentmanagement.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "clinical_documents")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClinicalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @Column(nullable = false)
    private Long encounterId;

    @Column(nullable = false)
    private Long patientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String attachmentUri;

    @Column(nullable = false)
    private Long authorUserId;

    private LocalDateTime signedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentStatus status;


    @PrePersist
    public void setDefaultStatus(){
        if(status == null){
            status = DocumentStatus.DRAFT;
        }
    }
}

package com.cts.caretrack.order_service.entity;


import com.cts.caretrack.order_service.enums.AbnormalFlag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Long orderId;
    private Long patientId;

    private String resultText;
    private String value;
    private String unit;
    private String referenceRange;

    @Enumerated(EnumType.STRING)
    private AbnormalFlag abnormalFlag;

    private LocalDateTime resultDate;
}

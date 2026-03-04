package com.cts.caretrack.order_service.dto;


import com.cts.caretrack.order_service.enums.AbnormalFlag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResultResponseDTO {

    private Long resultId;
    private Long orderId;
    private Long patientId;
    private String resultText;
    private String value;
    private String unit;
    private String referenceRange;
    private AbnormalFlag abnormalFlag;
    private LocalDateTime resultDate;
}

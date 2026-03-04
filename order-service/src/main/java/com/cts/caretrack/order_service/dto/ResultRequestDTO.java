package com.cts.caretrack.order_service.dto;


import com.cts.caretrack.order_service.enums.AbnormalFlag;
import lombok.Data;

@Data
public class ResultRequestDTO {

    private Long orderId;
    private Long patientId;
    private String resultText;
    private String value;
    private String unit;
    private String referenceRange;
    private AbnormalFlag abnormalFlag;
}

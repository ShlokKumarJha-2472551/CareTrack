package com.cts.caretrack.order_service.dto;


import com.cts.caretrack.order_service.enums.OrderType;
import lombok.Data;

@Data
public class OrderRequestDTO {

    private Long encounterId;
    private Long patientId;
    private Long providerId;
    private OrderType orderType;
    private String orderCode;
}

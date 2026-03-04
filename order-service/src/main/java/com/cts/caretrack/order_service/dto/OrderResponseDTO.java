package com.cts.caretrack.order_service.dto;


import com.cts.caretrack.order_service.enums.OrderStatus;
import com.cts.caretrack.order_service.enums.OrderType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Long encounterId;
    private Long patientId;
    private Long providerId;
    private OrderType orderType;
    private String orderCode;
    private LocalDateTime orderedDate;
    private OrderStatus status;
}

package com.cts.caretrack.order_service.entity;


import com.cts.caretrack.order_service.enums.OrderStatus;
import com.cts.caretrack.order_service.enums.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long encounterId;
    private Long patientId;
    private Long providerId;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private String orderCode;


    //private LocalDateTime orderDate;
    private LocalDateTime orderedDate;

    private OrderStatus status;
}

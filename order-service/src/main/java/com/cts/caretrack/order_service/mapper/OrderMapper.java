package com.cts.caretrack.order_service.mapper;

import com.cts.caretrack.order_service.dto.OrderRequestDTO;
import com.cts.caretrack.order_service.dto.OrderResponseDTO;
import com.cts.caretrack.order_service.entity.Orders;

public class OrderMapper {

    public static Orders toEntity(OrderRequestDTO dto){
        return Orders.builder()
                .encounterId(dto.getEncounterId())
                .patientId(dto.getPatientId())
                .providerId(dto.getProviderId())
                .orderType(dto.getOrderType())
                .orderCode(dto.getOrderCode())
                .build();
    }

    public static OrderResponseDTO toDTO(Orders order){
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .encounterId(order.getEncounterId())
                .patientId(order.getPatientId())
                .providerId(order.getProviderId())
                .orderType(order.getOrderType())
                .orderCode(order.getOrderCode())
                .orderedDate(order.getOrderedDate())
                .status(order.getStatus())
                .build();
    }
}

package com.cts.caretrack.order_service.service;

import com.cts.caretrack.order_service.dto.OrderRequestDTO;
import com.cts.caretrack.order_service.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getPatientOrders(Long patientId);
    OrderResponseDTO updateStatus(Long OrderId,String status);
    OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto);
    void deleteOrder(Long id);
}

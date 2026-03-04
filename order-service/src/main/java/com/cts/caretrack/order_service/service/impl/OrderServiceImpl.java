package com.cts.caretrack.order_service.service.impl;

import com.cts.caretrack.order_service.exception.ResourceNotFoundException;
import com.cts.caretrack.order_service.dto.OrderRequestDTO;
import com.cts.caretrack.order_service.dto.OrderResponseDTO;
import com.cts.caretrack.order_service.entity.Orders;
import com.cts.caretrack.order_service.enums.OrderStatus;
import com.cts.caretrack.order_service.mapper.OrderMapper;
import com.cts.caretrack.order_service.repository.OrderRepository;
import com.cts.caretrack.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.cts.caretrack.order_service.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repo;

    public OrderResponseDTO placeOrder(OrderRequestDTO dto){
        Orders order = OrderMapper.toEntity(dto);
        order.setOrderedDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        return OrderMapper.toDTO(repo.save(order));
    }

    public List<OrderResponseDTO> getPatientOrders(Long patientId){
        var list = repo.findByPatientId(patientId);
        if(list.isEmpty()) throw new ResourceNotFoundException("No orders found");
        return list.stream().map(OrderMapper::toDTO).toList();
    }

    public OrderResponseDTO updateStatus(Long id, String status){
        Orders order = repo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        try{
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        }
        catch(Exception e){
            throw new BadRequestException("Invalid status");
        }
        return OrderMapper.toDTO((repo.save(order)));
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {

        Orders order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setOrderCode(dto.getOrderCode());
        order.setOrderType(dto.getOrderType());
        order.setEncounterId(dto.getEncounterId());
        order.setProviderId(dto.getProviderId());

        return OrderMapper.toDTO(repo.save(order));
    }

    @Override
    public void deleteOrder(Long id) {

        Orders order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        repo.delete(order);
    }
}

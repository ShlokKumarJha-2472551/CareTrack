package com.cts.caretrack.order_service.controller;

import com.cts.caretrack.order_service.dto.OrderRequestDTO;
import com.cts.caretrack.order_service.dto.OrderResponseDTO;
import com.cts.caretrack.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PreAuthorize("hasAnyRole('PATIENT','CLINICIAN')")
    @PostMapping("/create-order")
    public ResponseEntity<OrderResponseDTO> place(@RequestBody OrderRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.placeOrder(dto));
    }

    @PreAuthorize("hasAnyRole('CLINICIAN','PATIENT')")
    @GetMapping("/patient/{id}")
    public List<OrderResponseDTO>get(@PathVariable Long id){
        return service.getPatientOrders(id);
    }


    @PreAuthorize("hasAnyRole('CLINICIAN','ADMIN')")
    @PutMapping("/{id}/status/{status}")
    public OrderResponseDTO update(@PathVariable Long id, @PathVariable String status){
        return service.updateStatus(id,status);
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(
            @PathVariable Long id,
            @RequestBody OrderRequestDTO dto) {

        return ResponseEntity.ok(service.updateOrder(id, dto));
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}

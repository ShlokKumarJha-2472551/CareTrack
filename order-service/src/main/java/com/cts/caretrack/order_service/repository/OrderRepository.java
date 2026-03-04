package com.cts.caretrack.order_service.repository;

import com.cts.caretrack.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByPatientId(Long patientId);
}

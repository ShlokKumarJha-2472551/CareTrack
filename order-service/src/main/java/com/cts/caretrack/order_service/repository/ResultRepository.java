package com.cts.caretrack.order_service.repository;

import com.cts.caretrack.order_service.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByOrderId(Long orderId);
}

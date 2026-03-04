package com.cts.caretrack.order_service.service;

import com.cts.caretrack.order_service.dto.ResultRequestDTO;
import com.cts.caretrack.order_service.dto.ResultResponseDTO;

import java.util.List;

public interface ResultService {

    ResultResponseDTO addResult(ResultRequestDTO dto);
    List<ResultResponseDTO> getResultsByOrder(Long orderId);
    ResultResponseDTO updateResult(Long id, ResultRequestDTO dto);
    void deleteResult(Long id);
}

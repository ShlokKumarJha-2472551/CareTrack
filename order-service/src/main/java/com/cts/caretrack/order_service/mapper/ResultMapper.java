package com.cts.caretrack.order_service.mapper;

import com.cts.caretrack.order_service.dto.ResultRequestDTO;
import com.cts.caretrack.order_service.dto.ResultResponseDTO;
import com.cts.caretrack.order_service.entity.Result;

public class ResultMapper {

    public static Result toEntity(ResultRequestDTO dto){
        return Result.builder()
                .orderId(dto.getOrderId())
                .patientId(dto.getPatientId())
                .resultText(dto.getResultText())
                .value(dto.getValue())
                .unit(dto.getUnit())
                .referenceRange(dto.getReferenceRange())
                .abnormalFlag(dto.getAbnormalFlag())
                .build();
    }

    public static ResultResponseDTO toDTO(Result result){
        return ResultResponseDTO.builder()
                .resultId(result.getResultId())
                .orderId(result.getOrderId())
                .patientId(result.getPatientId())
                .resultText(result.getResultText())
                .value(result.getValue())
                .unit(result.getUnit())
                .referenceRange(result.getReferenceRange())
                .abnormalFlag(result.getAbnormalFlag())
                .resultDate(result.getResultDate())
                .build();
    }
}

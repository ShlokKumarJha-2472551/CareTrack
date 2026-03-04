package com.cts.caretrack.order_service.service.impl;


import com.cts.caretrack.order_service.exception.ResourceNotFoundException;
import com.cts.caretrack.order_service.dto.ResultRequestDTO;
import com.cts.caretrack.order_service.dto.ResultResponseDTO;
import com.cts.caretrack.order_service.entity.Result;
import com.cts.caretrack.order_service.mapper.ResultMapper;
import com.cts.caretrack.order_service.repository.ResultRepository;
import com.cts.caretrack.order_service.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository repo;

    @Override
    public ResultResponseDTO addResult(ResultRequestDTO dto){
        Result r = ResultMapper.toEntity(dto);
        r.setResultDate(LocalDateTime.now());
        Result saved = repo.save(r);
        return ResultMapper.toDTO(saved);
    }

    @Override
    public List<ResultResponseDTO> getResultsByOrder(Long id){
        List<Result> list  = repo.findByOrderId(id);
        if(list.isEmpty()) throw new ResourceNotFoundException("No results found");
        return list.stream().map(ResultMapper::toDTO).toList();
    }

    @Override
    public ResultResponseDTO updateResult(Long id, ResultRequestDTO dto) {

        Result result = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));

        result.setResultText(dto.getResultText());
        result.setValue(dto.getValue());
        result.setUnit(dto.getUnit());
        result.setReferenceRange(dto.getReferenceRange());

        if (dto.getAbnormalFlag() != null) {
            result.setAbnormalFlag(dto.getAbnormalFlag());
        }

        Result updated = repo.save(result);
        return ResultMapper.toDTO(updated);
    }

    @Override
    public void deleteResult(Long id) {

        Result result = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));

        repo.delete(result);
    }
}

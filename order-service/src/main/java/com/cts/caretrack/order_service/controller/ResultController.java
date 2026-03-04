package com.cts.caretrack.order_service.controller;


import com.cts.caretrack.order_service.dto.ResultRequestDTO;
import com.cts.caretrack.order_service.dto.ResultResponseDTO;
import com.cts.caretrack.order_service.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PreAuthorize("hasRole('CLINICIAN')")
    @PostMapping("/add-result")
    public ResponseEntity<ResultResponseDTO>add(@RequestBody ResultRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.addResult(dto));
    }
    @PreAuthorize("hasAnyRole('CLINICIAN,'PATIENT')")
    @GetMapping("/order/{id}")
    public List<ResultResponseDTO>ger(@PathVariable Long id){
        return resultService.getResultsByOrder(id);
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ResultRequestDTO dto) {

        return ResponseEntity.ok(resultService.updateResult(id, dto));
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }

}

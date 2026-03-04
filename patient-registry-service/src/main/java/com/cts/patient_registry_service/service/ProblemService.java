package com.cts.patient_registry_service.service;
import com.cts.patient_registry_service.dto.ProblemDto;
import java.util.List;
public interface ProblemService {
    ProblemDto.Response addProblem(
            ProblemDto.CreateRequest request);
    List<ProblemDto.Response> getProblemsByPatient(
            Long patientId);
    ProblemDto.Response updateProblem(
            Long problemId,
            ProblemDto.UpdateRequest request);
}
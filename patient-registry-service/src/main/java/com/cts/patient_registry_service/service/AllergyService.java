package com.cts.patient_registry_service.service;

import com.cts.patient_registry_service.dto.AllergyDto;

import java.util.List;

public interface AllergyService {

    AllergyDto.Response addAllergy(

            AllergyDto.CreateRequest request);

    List<AllergyDto.Response> getAllergiesByPatient(

            Long patientId);

    void deleteAllergy(Long allergyId);

}

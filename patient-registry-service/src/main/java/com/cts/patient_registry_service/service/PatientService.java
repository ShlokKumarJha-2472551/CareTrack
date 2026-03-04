package com.cts.patient_registry_service.service;

import com.cts.patient_registry_service.dto.PatientDto;

import com.cts.patient_registry_service.entity.Patient;

import java.util.List;

public interface PatientService {

    PatientDto.Response createPatient(

            PatientDto.CreateRequest request);

    PatientDto.Response getPatientById(Long patientId);

    PatientDto.Response getPatientByMrn(String mrn);

    PatientDto.Response updatePatient(

            Long patientId,

            PatientDto.UpdateRequest request);

    void deactivatePatient(Long patientId);

    List<PatientDto.Summary> getAllPatients();

    List<PatientDto.Summary> getPatientsByStatus(

            Patient.PatientStatus status);

    List<PatientDto.Summary> searchPatients(String keyword);

}

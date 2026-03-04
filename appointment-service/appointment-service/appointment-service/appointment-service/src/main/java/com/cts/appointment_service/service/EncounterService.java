package com.cts.appointment_service.service;

import com.cts.appointment_service.dto.EncounterDto;
import com.cts.appointment_service.entity.Encounter.EncounterStatus;
import com.cts.appointment_service.entity.Encounter.EncounterType;
import java.util.List;

public interface EncounterService {
    EncounterDto.Response createEncounter(
            EncounterDto.CreateRequest request);
    EncounterDto.Response getEncounterById(Long id);
    List<EncounterDto.Response> getAllEncounters();
    List<EncounterDto.Response> getEncountersByPatient(
            Long patientId);
    List<EncounterDto.Response> getEncountersByType(
            EncounterType type);
    List<EncounterDto.Response> getEncountersByStatus(
            EncounterStatus status);
    EncounterDto.Response updateEncounter(
            Long id, EncounterDto.UpdateRequest request);
    void closeEncounter(Long id);
}

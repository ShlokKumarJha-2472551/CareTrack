package com.cts.appointment_service.service;

import com.cts.appointment_service.dto.AppointmentDto;
import com.cts.appointment_service.entity.Appointment.AppointmentStatus;
import java.util.List;

public interface AppointmentService {
    AppointmentDto.Response createAppointment(AppointmentDto.CreateRequest request);
    AppointmentDto.Response getAppointmentById(Long id);
    List<AppointmentDto.Response> getAllAppointments();
    List<AppointmentDto.Response> getAppointmentsByPatient(Long patientId);
    List<AppointmentDto.Response> getAppointmentsByStatus(AppointmentStatus status);
    AppointmentDto.Response updateAppointment(Long id, AppointmentDto.UpdateRequest request);
    void cancelAppointment(Long id);
    void checkInAppointment(Long id);
}

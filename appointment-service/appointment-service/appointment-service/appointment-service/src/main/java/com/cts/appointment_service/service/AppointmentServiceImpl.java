package com.cts.appointment_service.service;

import com.cts.appointment_service.dto.AppointmentDto;
import com.cts.appointment_service.entity.Appointment;
import com.cts.appointment_service.entity.Appointment.AppointmentStatus;
import com.cts.appointment_service.exception.ResourceNotFoundException;
import com.cts.appointment_service.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDto.Response createAppointment(
            AppointmentDto.CreateRequest request) {
        Appointment appointment = Appointment.builder()
                .patientId(request.getPatientId())
                .providerId(request.getProviderId())
                .scheduledDateTime(request.getScheduledDateTime())
                .reason(request.getReason())
                .status(AppointmentStatus.SCHEDULED)
                .build();
        return mapToResponse(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDto.Response getAppointmentById(Long id) {
        return mapToResponse(findById(id));
    }

    @Override
    public List<AppointmentDto.Response> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto.Response> getAppointmentsByPatient(
            Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto.Response> getAppointmentsByStatus(
            AppointmentStatus status) {
        return appointmentRepository.findByStatus(status)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto.Response updateAppointment(
            Long id, AppointmentDto.UpdateRequest request) {
        Appointment appointment = findById(id);
        if (request.getScheduledDateTime() != null)
            appointment.setScheduledDateTime(
                    request.getScheduledDateTime());
        if (request.getReason() != null)
            appointment.setReason(request.getReason());
        if (request.getStatus() != null)
            appointment.setStatus(request.getStatus());
        return mapToResponse(appointmentRepository.save(appointment));
    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment appointment = findById(id);
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void checkInAppointment(Long id) {
        Appointment appointment = findById(id);
        appointment.setStatus(AppointmentStatus.CHECKED_IN);
        appointmentRepository.save(appointment);
    }

    private Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with id: " + id));
    }

    private AppointmentDto.Response mapToResponse(Appointment a) {
        AppointmentDto.Response response = new AppointmentDto.Response();
        response.setAppointmentId(a.getAppointmentId());
        response.setPatientId(a.getPatientId());
        response.setProviderId(a.getProviderId());
        response.setScheduledDateTime(a.getScheduledDateTime());
        response.setReason(a.getReason());
        response.setStatus(a.getStatus());
        response.setCreatedAt(a.getCreatedAt());
        response.setUpdatedAt(a.getUpdatedAt());
        return response;
    }
}

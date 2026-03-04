package com.cts.appointment_service.repository;

import com.cts.appointment_service.entity.Appointment;
import com.cts.appointment_service.entity.Appointment.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByProviderId(Long providerId);
    List<Appointment> findByStatus(AppointmentStatus status);
    List<Appointment> findByPatientIdAndStatus(
            Long patientId, AppointmentStatus status);
}

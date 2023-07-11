package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleAppointmentService {
    void saveAppointment(ScheduleAppointment scheduleAppointment);

    List<ScheduleAppointment> sendRemainder(LocalDate today);

    List<ScheduleAppointment> findByAppointmentDate(LocalDate appointmentDate);

    ScheduleAppointment getScheduleById(Long appointmentId);

    void updateSchedule(Long appointmentId, ScheduleAppointment scheduleAppointment);

    Optional<ScheduleAppointment> findByAppointmentId(Long id);
}

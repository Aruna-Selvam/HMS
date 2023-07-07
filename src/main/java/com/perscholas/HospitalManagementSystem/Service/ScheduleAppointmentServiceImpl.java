package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleAppointmentServiceImpl implements ScheduleAppointmentService{
    @Autowired
    private ScheduleAppointmentRepository scheduleAppointmentRepository;
    @Override
    public void saveAppointment(ScheduleAppointment scheduleAppointment) {
        scheduleAppointmentRepository.save(scheduleAppointment);
    }

    @Override
    public List<ScheduleAppointment> sendRemainder(LocalDate today) {
        List<ScheduleAppointment> sa=scheduleAppointmentRepository.findByAppointmentDate(today);
        return sa;
    }

    @Override
    public List<ScheduleAppointment> findByAppointmentDate(LocalDate appointmentDate) {
        return null;
    }

    @Override
    public ScheduleAppointment getScheduleById(Long appointmentId) {
        return scheduleAppointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public void updateSchedule(ScheduleAppointment scheduleAppointment) {
        scheduleAppointmentRepository.save(scheduleAppointment);
    }
}
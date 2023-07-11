package com.perscholas.HospitalManagementSystem.repository;

import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleAppointmentRepository extends JpaRepository<ScheduleAppointment,Long> {

    boolean existsByAppointmentTime(LocalTime appointmentTime);

    boolean existsByDoctorName(String doctorName);

    List<ScheduleAppointment> findByAppointmentDate(LocalDate today);

    @Query("SELECT COUNT(sa) FROM ScheduleAppointment sa")
    int countAllAppointments();
}

package com.perscholas.HospitalManagementSystem.Repository;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleAppointmentRepository extends JpaRepository<ScheduleAppointment,Long> {

    boolean existsByAppointmentTime(LocalTime appointmentTime);

    boolean existsByDoctorName(String doctorName);

    List<ScheduleAppointment> findByAppointmentDate(LocalDate today);


}

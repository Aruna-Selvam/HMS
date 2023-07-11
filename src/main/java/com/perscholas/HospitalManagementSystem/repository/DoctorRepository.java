package com.perscholas.HospitalManagementSystem.repository;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT COUNT(d) FROM Doctor d")
    int countAllDoctors();
}

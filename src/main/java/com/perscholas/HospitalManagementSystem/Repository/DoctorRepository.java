package com.perscholas.HospitalManagementSystem.Repository;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}

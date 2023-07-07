package com.perscholas.HospitalManagementSystem.Repository;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByPatientId(Long patientId);

@Query("SELECT p FROM Patient p WHERE lower(p.name) LIKE %:searchText% " +
        "OR lower(p.email) LIKE %:searchText% " +
        "OR lower(p.contactNumber) LIKE %:searchText% " +
        "OR lower(p.dateOfBirth) LIKE %:searchText% " +
        "OR lower(p.gender) LIKE %:searchText% " +
        "OR lower(p.address) LIKE %:searchText% " +
        "OR lower(p.patientId) LIKE %:searchText%")
List<Patient> findByNameContainingIgnoreCase(String searchText);
}

package com.perscholas.HospitalManagementSystem.startup;

import com.perscholas.HospitalManagementSystem.Entity.Department;
import com.perscholas.HospitalManagementSystem.Entity.Doctor;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.repository.DepartmentRepository;
import com.perscholas.HospitalManagementSystem.repository.DoctorRepository;
import com.perscholas.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInt implements ApplicationListener<ApplicationReadyEvent> {

    private final PatientRepository patientRepository;

    @Autowired
    public DataInt(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initializeData();
    }

    public void initializeData() {
        // Check if data is already initialized
        if (patientRepository.count() == 0) {
            Patient patient1 = new Patient();
            patient1.setName("John Smith");
            patient1.setDateOfBirth(java.sql.Date.valueOf(LocalDate.of(1990, 5, 15)));
            patient1.setGender("Male");
            patient1.setEmail("john@example.com");
            patient1.setContactNumber("1234567890");
            patient1.setAddress("123 Street, City");

            Patient patient2 = new Patient();
            patient2.setName("Alice Johnson");
            patient2.setDateOfBirth(java.sql.Date.valueOf(LocalDate.of(1985, 8, 10)));
            patient2.setGender("Female");
            patient2.setEmail("alice@example.com");
            patient2.setContactNumber("9876543210");
            patient2.setAddress("456 Street, City");

            Patient patient3 = new Patient();
            patient3.setName("Jane Doe");
            patient3.setDateOfBirth(java.sql.Date.valueOf(LocalDate.of(1992, 3, 20)));
            patient3.setGender("Female");
            patient3.setEmail("jane@example.com");
            patient3.setContactNumber("5555555555");
            patient3.setAddress("789 Street, City");

            Patient patient4 = new Patient();
            patient4.setName("Michael Smith");
            patient4.setDateOfBirth(java.sql.Date.valueOf(LocalDate.of(1978, 11, 25)));
            patient4.setGender("Male");
            patient4.setEmail("michael@example.com");
            patient4.setContactNumber("1112223333");
            patient4.setAddress("321 Street, City");

            Patient patient5 = new Patient();
            patient5.setName("Emily Davis");
            patient5.setDateOfBirth(java.sql.Date.valueOf(LocalDate.of(1982, 7, 8)));
            patient5.setGender("Female");
            patient5.setEmail("emily@example.com");
            patient5.setContactNumber("4444444444");
            patient5.setAddress("654 Street, City");

            patientRepository.saveAll(List.of(patient1,patient2,patient3,patient4,patient5));

        }
    }

    public void destroyData() {
        // Delete all patient and related entities
        patientRepository.deleteAll();

    }
}

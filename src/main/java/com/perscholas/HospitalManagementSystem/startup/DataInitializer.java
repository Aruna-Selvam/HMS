package com.perscholas.HospitalManagementSystem.startup;

import com.perscholas.HospitalManagementSystem.Entity.Department;
import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Repository.DepartmentRepository;
import com.perscholas.HospitalManagementSystem.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DataInitializer(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initializeData();
    }

    public void initializeData() {
        // Check if data is already initialized
        if (doctorRepository.count() == 0) {
            // Create department
            Department department = new Department();
            department.setDepartmentName("Cardiology");
            departmentRepository.save(department);

            // Create doctors
            List<Doctor> doctors = new ArrayList<>();

            // Create doctors
            Doctor doctor1 = new Doctor();
            doctor1.setDoctorName("Dr. John Milan");
            doctor1.setSpecialization("Cardiology");
            doctor1.setPhoneNumber("1234567890");
            doctor1.setEmail("johnmilan@gmail.com");
            doctor1.setDepartment(department);

            Doctor doctor2 = new Doctor();
            doctor2.setDoctorName("Dr. James Patterson");
            doctor2.setSpecialization("Pediatrics");
            doctor2.setPhoneNumber("9876543210");
            doctor2.setEmail("jamesPat@gmail.com");
            doctor2.setDepartment(department);

            Doctor doctor3 = new Doctor();
            doctor3.setDoctorName("Dr. Michael Johnson");
            doctor3.setSpecialization("Dermatology");
            doctor3.setPhoneNumber("5551234567");
            doctor3.setEmail("michaeljohnson@gmail.com");
            doctor3.setDepartment(department);

            Doctor doctor4 = new Doctor();
            doctor4.setDoctorName("Dr. Emily Anderson");
            doctor4.setSpecialization("Gynecology");
            doctor4.setPhoneNumber("8889876543");
            doctor4.setEmail("emilyanderson@gmail.com");
            doctor4.setDepartment(department);

            // Save doctors to the database
            doctorRepository.saveAll(List.of(doctor1, doctor2, doctor3, doctor4));
        }
    }

    public void destroyData() {
        // Delete all doctors and related entities
        doctorRepository.deleteAll();
        departmentRepository.deleteAll();
    }
}

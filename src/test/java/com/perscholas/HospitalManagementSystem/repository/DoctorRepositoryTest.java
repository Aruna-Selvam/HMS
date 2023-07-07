package com.perscholas.HospitalManagementSystem.repository;
import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testSaveDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorName("John Smith");

        // Act
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Assert
        assertNotNull(savedDoctor.getDoctorId());
        assertEquals(doctor.getDoctorName(), savedDoctor.getDoctorName());
    }

    @Test
    public void testFindById_ExistingDoctorId_ReturnsDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorName("John Smith");
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Act
        Optional<Doctor> foundDoctor = doctorRepository.findById(savedDoctor.getDoctorId());

        // Assert
        assertTrue(foundDoctor.isPresent());
        assertEquals(savedDoctor.getDoctorId(), foundDoctor.get().getDoctorId());
        assertEquals(savedDoctor.getDoctorName(), foundDoctor.get().getDoctorName());
    }

    @Test
    public void testFindById_NonExistingDoctorId_ReturnsEmptyOptional() {
        // Arrange

        // Act
        Optional<Doctor> foundDoctor = doctorRepository.findById(999L);

        // Assert
        assertFalse(foundDoctor.isPresent());
    }

    @Test
    public void testFindAll_ReturnsAllDoctors() {
        // Arrange
        Doctor doctor1 = new Doctor();
        doctor1.setDoctorName("John Smith");
        Doctor doctor2 = new Doctor();
        doctor2.setDoctorName("Jane Doe");
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        // Act
        List<Doctor> doctors = doctorRepository.findAll();

        // Assert
        assertTrue(doctors.contains(doctor1));
        assertTrue(doctors.contains(doctor2));
    }

    @Test
    public void testDeleteById_ExistingDoctorId_DeletesDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorName("John Smith");
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Act
        doctorRepository.deleteById(savedDoctor.getDoctorId());

        // Assert
        assertFalse(doctorRepository.existsById(savedDoctor.getDoctorId()));
    }
}

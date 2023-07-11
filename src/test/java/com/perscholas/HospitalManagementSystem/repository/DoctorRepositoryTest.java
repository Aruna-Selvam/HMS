package com.perscholas.HospitalManagementSystem.repository;
import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class DoctorRepositoryTest {

    @Mock
    private DoctorRepository doctorRepository;

    public DoctorRepositoryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorId(2L);
        doctor.setDoctorName("John Smith");
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

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
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));

        // Act
        Optional<Doctor> foundDoctor = doctorRepository.findById(1L);

        // Assert
        assertTrue(foundDoctor.isPresent());
        assertEquals(doctor.getDoctorId(), foundDoctor.get().getDoctorId());
        assertEquals(doctor.getDoctorName(), foundDoctor.get().getDoctorName());
    }

    @Test
    public void testFindById_NonExistingDoctorId_ReturnsEmptyOptional() {
        // Arrange
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.empty());

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
        List<Doctor> expectedDoctors = new ArrayList<>();
        expectedDoctors.add(doctor1);
        expectedDoctors.add(doctor2);
        when(doctorRepository.findAll()).thenReturn(expectedDoctors);

        // Act
        List<Doctor> doctors = doctorRepository.findAll();

        // Assert
        assertEquals(expectedDoctors.size(), doctors.size());
        assertTrue(doctors.contains(doctor1));
        assertTrue(doctors.contains(doctor2));
    }

    @Test
    public void testDeleteById_ExistingDoctorId_DeletesDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorName("John Smith");
        doNothing().when(doctorRepository).deleteById(anyLong());

        // Act
        doctorRepository.deleteById(1L);

        // Assert
        verify(doctorRepository, times(1)).deleteById(1L);
    }
}

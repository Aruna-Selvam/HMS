package com.perscholas.HospitalManagementSystem.repository;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScheduleAppointmentRepositoryTest {
    @Mock
    private ScheduleAppointmentRepository scheduleAppointmentRepository;

    private ScheduleAppointment mockScheduleAppointment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockScheduleAppointment = setMockScheduleAppointment();
    }

    private ScheduleAppointment setMockScheduleAppointment() {
        Long appointmentId = 1L;
        String patientName = "John Smith";
        String patientPhoneNumber = "1234567890";
        String eMail = "john@example.com";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String doctorName = "Smith";
        LocalDate appointmentDate = LocalDate.of(2023, 7, 10);
        LocalTime appointmentTime = LocalTime.of(10, 30);
        Doctor doctor = new Doctor("Smith", "Cardiology");
        Patient patient = new Patient("John Smith", "1234567890", "john@example.com", LocalDate.of(1990, 1, 1));

        ScheduleAppointment appointment = new ScheduleAppointment(appointmentId, patientName, patientPhoneNumber, eMail, dateOfBirth, doctorName, appointmentDate, appointmentTime, doctor, patient);
        return appointment;
    }

    @Test
    public void testExistsByAppointmentTime_ExistingAppointmentTime_ReturnsTrue() {
        // Arrange
        LocalTime appointmentTime = LocalTime.of(10, 30);
        when(scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime)).thenReturn(true);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime);

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByAppointmentTime_NonExistingAppointmentTime_ReturnsFalse() {
        // Arrange
        LocalTime appointmentTime = LocalTime.of(14, 0);
        when(scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime)).thenReturn(false);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime);

        // Assert
        assertFalse(exists);
    }

    @Test
    public void testExistsByDoctorName_ExistingDoctorName_ReturnsTrue() {
        // Arrange
        String doctorName = "Smith";
        when(scheduleAppointmentRepository.existsByDoctorName(doctorName)).thenReturn(true);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByDoctorName(doctorName);

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByDoctorName_NonExistingDoctorName_ReturnsFalse() {
        // Arrange
        String doctorName = "Dr. John Smith";
        when(scheduleAppointmentRepository.existsByDoctorName(doctorName)).thenReturn(false);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByDoctorName(doctorName);

        // Assert
        assertFalse(exists);
    }

//    @Test
//    public void testFindByAppointmentDate_ValidDate_ReturnsAppointments() {
//        // Arrange
//        LocalDate appointmentDate = LocalDate.now();
//        List<ScheduleAppointment> expectedAppointments = /* Create your expected appointment list here */ null;
//        when(scheduleAppointmentRepository.findByAppointmentDate(appointmentDate)).thenReturn(expectedAppointments);
//
//        // Act
//        List<ScheduleAppointment> appointments = scheduleAppointmentRepository.findByAppointmentDate(appointmentDate);
//
//        // Assert
//        assertNotNull(appointments);
//        // Add your assertions for the expected appointments list
//    }
@Test
public void testFindByAppointmentDate_ValidDate_ReturnsAppointments() {
    // Arrange
    LocalDate appointmentDate = LocalDate.now();
    List<ScheduleAppointment> expectedAppointments = new ArrayList<>();

    // Create mock ScheduleAppointment objects and add them to the expected list
    ScheduleAppointment appointment1 = new ScheduleAppointment();
    // Set properties for appointment1
    appointment1.setAppointmentId(1L);
    appointment1.setPatientName("John Smith");
    appointment1.setPatientPhoneNumber("1234567890");
    appointment1.setEMail("john@example.com");
    appointment1.setDateOfBirth(LocalDate.of(1990, 1, 1));
    appointment1.setDoctorName("Smith");
    appointment1.setAppointmentDate(appointmentDate);
    appointment1.setAppointmentTime(LocalTime.of(10, 30));
    expectedAppointments.add(appointment1);

    ScheduleAppointment appointment2 = new ScheduleAppointment();
    appointment2.setAppointmentId(2L);
    appointment2.setPatientName("Jane Doe");
    appointment2.setPatientPhoneNumber("9876543210");
    appointment2.setEMail("jane@example.com");
    appointment2.setDateOfBirth(LocalDate.of(1985, 5, 15));
    appointment2.setDoctorName("Johnson");
    appointment2.setAppointmentDate(appointmentDate);
    appointment2.setAppointmentTime(LocalTime.of(14, 0));
    // Set other properties for appointment2
    // Set properties for appointment2
    expectedAppointments.add(appointment2);

    // Mock the behavior of the scheduleAppointmentRepository
    when(scheduleAppointmentRepository.findByAppointmentDate(appointmentDate)).thenReturn(expectedAppointments);

    // Act
    List<ScheduleAppointment> appointments = scheduleAppointmentRepository.findByAppointmentDate(appointmentDate);

    // Assert
    assertNotNull(appointments);
    assertEquals(expectedAppointments.size(), appointments.size());
    // Add more specific assertions if needed
}

}

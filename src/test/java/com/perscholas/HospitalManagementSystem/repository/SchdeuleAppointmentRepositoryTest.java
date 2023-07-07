package com.perscholas.HospitalManagementSystem.repository;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ScheduleAppointmentRepositoryTest {
    @Autowired
    private ScheduleAppointmentRepository scheduleAppointmentRepository;

    private ScheduleAppointment mockScheduleAppointment;

    @BeforeEach
    public void setup() {
        mockScheduleAppointment =setMockScheduleAppointment();
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
//        scheduleAppointmentRepository.save(mockScheduleAppointment);
        // Arrange
        LocalTime appointmentTime = LocalTime.of(10, 30);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime);

        // Assert
        assertTrue(exists);
    }
    @Test
    public void testExistsByAppointmentTime_NonExistingAppointmentTime_ReturnsFalse() {
        // Arrange
        LocalTime appointmentTime = LocalTime.of(14, 0);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByAppointmentTime(appointmentTime);

        // Assert
        assertFalse(exists);
    }
    @Test
    public void testExistsByDoctorName_ExistingDoctorName_ReturnsTrue() {
        String doctorName = "Smith";

        // Create a ScheduleAppointment with the specified doctor name and save it to the database
        ScheduleAppointment scheduleAppointment = new ScheduleAppointment();
        scheduleAppointment.setDoctorName(doctorName);
        scheduleAppointmentRepository.save(scheduleAppointment);

        // Act
        boolean exists = scheduleAppointmentRepository.existsByDoctorName(doctorName);

        // Assert
        assertTrue(exists);

    }

    @Test
    public void testExistsByDoctorName_NonExistingDoctorName_ReturnsFalse() {
        // Arrange
        String doctorName = "Dr. John Smith";

        // Act
        boolean exists = scheduleAppointmentRepository.existsByDoctorName(doctorName);

        // Assert
        assertFalse(exists);
    }
    @Test
    public void testFindByAppointmentDate_ValidDate_ReturnsAppointments() {
        // Arrange
        LocalDate appointmentDate = LocalDate.now();

        // Act
        List<ScheduleAppointment> appointments = scheduleAppointmentRepository.findByAppointmentDate(appointmentDate);

        // Assert
        assertNotNull(appointments);
        assertEquals(1, appointments.size());

    }

}

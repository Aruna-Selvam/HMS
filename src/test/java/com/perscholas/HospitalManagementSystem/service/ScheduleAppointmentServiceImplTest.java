package com.perscholas.HospitalManagementSystem.service;

import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import com.perscholas.HospitalManagementSystem.Service.ScheduleAppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleAppointmentServiceImplTest {

    @Mock
    private ScheduleAppointmentRepository scheduleAppointmentRepository;

    @InjectMocks
    private ScheduleAppointmentServiceImpl scheduleAppointmentService;

    private ScheduleAppointment scheduleAppointment;

    @BeforeEach
    public void setUp() {
        scheduleAppointment = new ScheduleAppointment();
        // Set up the scheduleAppointment object if needed for specific test cases
    }

    @Test
    @DisplayName("Test For Save Appointment ")
    public void testSaveAppointment() {
        scheduleAppointmentService.saveAppointment(scheduleAppointment);
        verify(scheduleAppointmentRepository, times(1)).save(scheduleAppointment);
    }

    @Test
    @DisplayName("Test for Sending Remainder ")
    public void testSendReminder() {
        LocalDate today = LocalDate.now();
        List<ScheduleAppointment> appointments = new ArrayList<>();
        appointments.add(scheduleAppointment);

        when(scheduleAppointmentRepository.findByAppointmentDate(today)).thenReturn(appointments);

        List<ScheduleAppointment> result = scheduleAppointmentService.sendRemainder(today);

        assertEquals(appointments, result);
        verify(scheduleAppointmentRepository, times(1)).findByAppointmentDate(today);
    }

    @Test
    @DisplayName("Test For Get Schedule By Id")
    public void testGetScheduleById() {
        Long appointmentId = 1L;
        when(scheduleAppointmentRepository.findById(appointmentId)).thenReturn(java.util.Optional.ofNullable(scheduleAppointment));

        ScheduleAppointment result = scheduleAppointmentService.getScheduleById(appointmentId);

        assertEquals(scheduleAppointment, result);
        verify(scheduleAppointmentRepository, times(1)).findById(appointmentId);
    }

//    @Test
//    @DisplayName("Test For Update Schedule")
//    public void testUpdateSchedule() {
//        scheduleAppointmentService.updateSchedule(appointmentId,scheduleAppointment);
//        verify(scheduleAppointmentRepository, times(1)).save(scheduleAppointment);
//    }
}

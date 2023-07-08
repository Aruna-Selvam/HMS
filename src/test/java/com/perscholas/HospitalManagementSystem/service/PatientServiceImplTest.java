package com.perscholas.HospitalManagementSystem.service;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Repository.PatientRepository;
import com.perscholas.HospitalManagementSystem.Service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();

    }

    @Test
    @DisplayName("Test For Save Patient ")
    public void testSavePatient() {
        patientService.savePatient(patient);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    @DisplayName("Test For Get All Patients")
    public void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(patients, result);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test For Get Patient By Id ")
    public void testGetPatientById_ExistingPatient_ReturnsPatient() {
        Long patientId = 1L;
        when(patientRepository.findByPatientId(patientId)).thenReturn(patient);

        Patient result = patientService.getPatientById(patientId);

        assertEquals(patient, result);
        verify(patientRepository, times(1)).findByPatientId(patientId);
    }

    @Test
    @DisplayName("Test for Get Patient By Id Not Exist ")
    public void testGetPatientById_NonExistingPatient_ThrowsRuntimeException() {
        Long patientId = 1L;
        when(patientRepository.findByPatientId(patientId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> patientService.getPatientById(patientId));
        verify(patientRepository, times(1)).findByPatientId(patientId);
    }

    @Test
    @DisplayName("Test For Update Patient ")
    public void testUpdatePatientById() throws IOException {
        Long patientId = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setPatientId(patientId);

        when(patientRepository.findByPatientId(patientId)).thenReturn(existingPatient);
        when(patientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test file content".getBytes());
        Patient updatedPatient = new Patient();
        updatedPatient.setName("John Doe");
        updatedPatient.setEmail("john@gmail.com");
        Patient result = patientService.updatePatientById(patientId, updatedPatient, file);

        assertEquals(existingPatient, result);
        assertEquals("John Doe", result.getName());

        verify(patientRepository, times(1)).findByPatientId(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    @DisplayName("Test For Delete Patient By Id ")
    public void testDeletePatientById() {
        Long patientId = 1L;
        patientService.deletePatientById(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);
    }

    @Test
    @DisplayName("Test For Search Patient Details ")
    public void testSearchPatients() {
        String searchText = "John";
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);

        when(patientRepository.findByNameContainingIgnoreCase(searchText)).thenReturn(patients);

        List<Patient> result = patientService.searchPatients(searchText);

        assertEquals(patients, result);
        verify(patientRepository, times(1)).findByNameContainingIgnoreCase(searchText);
    }
}

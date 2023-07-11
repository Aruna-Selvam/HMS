package com.perscholas.HospitalManagementSystem.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientRepositoryTest {

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testFindByNameContainingIgnoreCase_PartialName_ReturnsMatchingPatients() {
        // Arrange
        String partialName = "John";
        Patient patient1 = new Patient(1L, "John Smith", null, null, null, null, null, null, null);
        Patient patient2 = new Patient(2L, "John Doe", null, null, null, null, null, null, null);
        List<Patient> expectedPatients = Arrays.asList(patient1, patient2);

        when(patientRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientRepository.findByNameContainingIgnoreCase(partialName);

        // Assert
        assertEquals(expectedPatients.size(), actualPatients.size());
        assertEquals(expectedPatients, actualPatients);
    }
}

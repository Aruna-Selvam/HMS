package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientService {
    void savePatient(Patient patient);

    List<Patient> getAllPatients();
    Patient getPatientById(Long patientId);


    Patient updatePatientById(Long patientId, Patient patient, MultipartFile file);
    void deletePatientById(Long patientId);

    List<Patient> searchPatients(String searchText);


}

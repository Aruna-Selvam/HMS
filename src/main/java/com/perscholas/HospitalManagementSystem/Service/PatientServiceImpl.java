package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements  PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void savePatient(Patient patient) {

        patientRepository.save(patient);

    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long patientId) {
        Optional<Patient> optional = Optional.ofNullable(patientRepository.findByPatientId(patientId));
        Patient patient=null;
        if(optional.isPresent()){
            patient=optional.get();
        }
       else {
           throw new RuntimeException("Patient Not Found");
        }
      return patient;
    }

    @Override
    public Patient updatePatientById(Long patientId, Patient patient, MultipartFile file) {
        Patient existingPatient = patientRepository.findByPatientId(patientId);
        existingPatient.setName(patient.getName());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setGender(patient.getGender());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setContactNumber(patient.getContactNumber());
        existingPatient.setAddress(patient.getAddress());

        if (!file.isEmpty()) {
            try {
                existingPatient.setFileData(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception as per your application's requirements
            }
        }

        // Save the updated patient
        patientRepository.save(existingPatient);
        return  existingPatient;
    }

    @Override
    public void deletePatientById(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<Patient> searchPatients(String searchText) {
        return patientRepository.findByNameContainingIgnoreCase(searchText);
    }


}

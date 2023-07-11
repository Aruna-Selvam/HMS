package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public List<Doctor> getAllDoctors() {
       return doctorRepository.findAll();
    }
}

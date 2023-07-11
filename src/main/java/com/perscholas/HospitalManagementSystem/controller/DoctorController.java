package com.perscholas.HospitalManagementSystem.controller;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    // Handler method for displaying the appointment form
    @GetMapping("/appointment")
    public String showAppointmentForm(Model model) {
        try{
            // Retrieve the list of doctors from the repository
        List<Doctor> doctors = doctorRepository.findAll();
            // Add the list of doctors to the model
        model.addAttribute("doctors", doctors);
        return "appointment-form";}
        catch (Exception e) {
            // Handle the exception and display an error message
            model.addAttribute("errorMessage", "Failed to load doctor information");
            return "error_page";
        }
    }
}

package com.perscholas.HospitalManagementSystem.Controller;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/appointment")
    public String showAppointmentForm(Model model) {
        try{
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "appointment-form";}
        catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to load doctor information");
            return "error_page";
        }
    }
}

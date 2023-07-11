package com.perscholas.HospitalManagementSystem.Controller;

import com.perscholas.HospitalManagementSystem.Repository.DoctorRepository;
import com.perscholas.HospitalManagementSystem.Repository.PatientRepository;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final PatientRepository patientRepository;
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;
    private final DoctorRepository doctorRepository;

    public AdminController(PatientRepository patientRepository, ScheduleAppointmentRepository scheduleAppointmentRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.scheduleAppointmentRepository = scheduleAppointmentRepository;
        this.doctorRepository = doctorRepository;
    }
    @GetMapping("/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }

    @GetMapping("/statistics")
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();

        int totalPatients = patientRepository.countAllPatients();
        int totalAppointments = scheduleAppointmentRepository.countAllAppointments();
        int totalDoctors = doctorRepository.countAllDoctors();

        statistics.put("totalPatients", totalPatients);
        statistics.put("totalAppointments", totalAppointments);
        statistics.put("totalDoctors", totalDoctors);

        return statistics;
    }
}

package com.perscholas.HospitalManagementSystem.controller;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.exception.AppointmentTimeAlreadyExistsException;
import com.perscholas.HospitalManagementSystem.repository.DoctorRepository;
import com.perscholas.HospitalManagementSystem.repository.ScheduleAppointmentRepository;
import com.perscholas.HospitalManagementSystem.Service.DoctorService;
import com.perscholas.HospitalManagementSystem.Service.EmailService;
import com.perscholas.HospitalManagementSystem.Service.EmailServiceImpl;
import com.perscholas.HospitalManagementSystem.Service.ScheduleAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ScheduleAppointmentController {
    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ScheduleAppointmentRepository scheduleAppointmentRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @GetMapping("/app")
    public String getScheduleForm(Model model){
        ScheduleAppointment scheduleAppointment=new ScheduleAppointment();
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        model.addAttribute("scheduleAppointment",scheduleAppointment);
        return "schedule";
    }
    @PostMapping("/app")
    public String processAppointmentForm(@ModelAttribute("scheduleAppointment")  ScheduleAppointment scheduleAppointment ) {
        boolean exists = scheduleAppointmentRepository.existsByAppointmentTime(scheduleAppointment.getAppointmentTime());
        boolean exists1 = scheduleAppointmentRepository.existsByDoctorName(scheduleAppointment.getDoctorName());
   if (exists && exists1) {
       throw new AppointmentTimeAlreadyExistsException();
      }

   else{
       List<Doctor> doctors = doctorRepository.findAll();
       scheduleAppointment.setDoctorIdByName(doctors);
       // Save the appointment in the database
        scheduleAppointmentService.saveAppointment(scheduleAppointment);
        emailServiceImpl.sendEmail(scheduleAppointment.getEMail(),
                "Appointment Scheduled"," Your appointment with "+scheduleAppointment.getDoctorName()+
                        " is scheduled at "+scheduleAppointment.getAppointmentTime()+ " on "+
                        scheduleAppointment.getAppointmentDate() +" . ");
        return "schedule_success";}
    }
    @GetMapping("/schedule/update")
    public String getScheduleByAppointmentId(@RequestParam("appointmentId")  Long appointmentId, Model model)
    {
        ScheduleAppointment scheduleAppointment1=new ScheduleAppointment();
        try{
        ScheduleAppointment scheduleAppointment=scheduleAppointmentService.getScheduleById(appointmentId);
            List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("scheduleAppointment",scheduleAppointment);
            model.addAttribute("appointmentId", appointmentId);
            model.addAttribute("doctors", doctors);
        return "update_schedule";}
        catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Schedule appointment not found");
            return "error_page";
        }
    }
    @PostMapping("/schedule/update/{appointmentId}")
    public String updateSchedule(@PathVariable("appointmentId") Long appointmentId, @ModelAttribute("scheduleAppointment") ScheduleAppointment scheduleAppointment, Model model) {
        try{
        //Update the schedule using scheduleAppointmentService
        scheduleAppointmentService.updateSchedule(appointmentId,scheduleAppointment);

        // Add a success message to the model to be displayed on the update_schedule view
           model.addAttribute("successMessage", "Schedule updated successfully!");

        // Redirect to a relevant page or return the update_schedule view
        return "sc_success";}
        catch (RuntimeException e) {
            // Handle the exception and display an error message
            model.addAttribute("errorMessage", "Failed to update the schedule");

            // Redirect to an error page or return an error view
            return "error_page";}
    }
    @GetMapping("/schedule/delete")
    public String deletePatientById(Model model, Long appointmentId) {
        Patient patient=new Patient();
        scheduleAppointmentRepository.deleteById(appointmentId);
        return "sche_success";

    }
    @Scheduled(cron = "0 0 10 * * ?") // Run the reminder task every day at 10:00 PM
    private void sendReminderEmails() {
        LocalDate currentDate = LocalDate.now();
        LocalDate reminderDate = currentDate.plusDays(1); // Calculate the reminder date as tomorrow

        List<ScheduleAppointment> appointments = scheduleAppointmentRepository.findByAppointmentDate(reminderDate);

        for (ScheduleAppointment appointment : appointments) {
            // Create and send the reminder email for each appointment
            emailService.sendEmail(appointment.getEMail(),
                    "Appointment Reminder",
                    "Your appointment with " + appointment.getDoctorName() +
                            " is scheduled tomorrow at " + appointment.getAppointmentTime() +
                            " on " + appointment.getAppointmentDate() + ".");
        }
    }
}

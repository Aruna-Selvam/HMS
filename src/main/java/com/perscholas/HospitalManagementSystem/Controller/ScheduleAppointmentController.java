package com.perscholas.HospitalManagementSystem.Controller;

import com.perscholas.HospitalManagementSystem.Entity.Doctor;
import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Entity.ScheduleAppointment;
import com.perscholas.HospitalManagementSystem.Exception.AppointmentTimeAlreadyExistsException;
import com.perscholas.HospitalManagementSystem.Repository.DoctorRepository;
import com.perscholas.HospitalManagementSystem.Repository.ScheduleAppointmentRepository;
import com.perscholas.HospitalManagementSystem.Service.EmailService;
import com.perscholas.HospitalManagementSystem.Service.EmailServiceImpl;
import com.perscholas.HospitalManagementSystem.Service.ScheduleAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
//        boolean exists = scheduleAppointmentRepository.existsByAppointmentTimeAndDoctor(
//              scheduleAppointment.getAppointmentTime(), scheduleAppointment.getDoctor());

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
        return "success";}
    }
    @GetMapping("/schedule/")
    public String getScheduleByAppointmentId(@RequestParam("appointmentId")  Long appointmentId, Model model)
    {
        ScheduleAppointment scheduleAppointment=new ScheduleAppointment();
        ScheduleAppointment scheduleAppointment1=scheduleAppointmentService.getScheduleById(appointmentId);
        model.addAttribute("scheduleAppointment",scheduleAppointment);
        return "update_schedule";
    }
    @PostMapping("/schedule/update")
    public String updateSchedule(@ModelAttribute("scheduleAppointment") ScheduleAppointment scheduleAppointment, Model model) {
        // Perform the necessary logic to update the schedule with the provided data
        // You can access the properties of the scheduleAppointment object, such as scheduleAppointment.getPatientName(), scheduleAppointment.getAppointmentDate(), etc.
        // Update the schedule using the scheduleAppointmentService or any other service or repository

        // Example: Update the schedule using scheduleAppointmentService
        scheduleAppointmentService.updateSchedule(scheduleAppointment);

        // Add a success message to the model to be displayed on the update_schedule view
        model.addAttribute("successMessage", "Schedule updated successfully!");

        // Redirect to a relevant page or return the update_schedule view
        return "redirect:/schedule/";
    }
    @Scheduled(cron = "0 0 12 * * ?") // Run the reminder task every day at 12:00 PM
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

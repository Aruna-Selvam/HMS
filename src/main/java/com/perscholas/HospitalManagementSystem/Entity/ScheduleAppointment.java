package com.perscholas.HospitalManagementSystem.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor

public class ScheduleAppointment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long appointmentId;
    @NotNull
    private String patientName;
    @NotNull
    private String patientPhoneNumber;
    @NotNull
    private String eMail;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

//    @Column(name="doctor_id")
//    private Long doctorId;
    @NotNull
    private String doctorName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime appointmentTime;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public ScheduleAppointment(Long appointmentId, String patientName, String patientPhoneNumber, String eMail, LocalDate dateOfBirth, String doctorName, LocalDate appointmentDate, LocalTime appointmentTime, Doctor doctor, Patient patient) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.eMail = eMail;
        this.dateOfBirth = dateOfBirth;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
    }

    public void setDoctorIdByName(List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorName().equals(doctorName)) {
                this.doctor = doctor;
                break;
            }
        }
    }
}

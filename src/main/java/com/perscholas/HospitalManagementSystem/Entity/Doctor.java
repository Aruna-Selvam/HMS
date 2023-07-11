package com.perscholas.HospitalManagementSystem.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    @NotBlank(message = "Specialization is required")
    private String specialization;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phoneNumber;


    private String email;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "doctor")
    private List<ScheduleAppointment> appointments;
    public Doctor(Long doctorId, String doctorName, String specialization, String phoneNumber, String email, Department department, List<ScheduleAppointment> appointments) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.appointments = appointments;
    }

    public Doctor(String s, String cardiology) {
    }
}

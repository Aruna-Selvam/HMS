package com.perscholas.HospitalManagementSystem.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    private String doctorName;
    private String specialization;
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

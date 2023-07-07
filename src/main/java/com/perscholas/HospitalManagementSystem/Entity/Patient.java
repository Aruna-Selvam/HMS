package com.perscholas.HospitalManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String contactNumber;
    private String address;
    @Lob
    private byte[] fileData;
    @OneToMany(mappedBy = "patient")
    private List<ScheduleAppointment> appointments;

    public Patient(String john_smith, String s, String s1, LocalDate of) {
    }


    public Patient(long l, String alice_johnson, LocalDate of, String female, String s, String s1, String s2) {
    }

    public Patient(String jane_doe, LocalDate of, String female, String s, String s1, String s2) {
    }
}

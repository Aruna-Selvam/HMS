package com.perscholas.HospitalManagementSystem.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long departmentId;
    private String departmentName;
    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;
}

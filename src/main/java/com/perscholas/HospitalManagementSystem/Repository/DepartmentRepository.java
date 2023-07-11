package com.perscholas.HospitalManagementSystem.Repository;

import com.perscholas.HospitalManagementSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}

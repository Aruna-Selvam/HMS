package com.perscholas.HospitalManagementSystem.repository;

import com.perscholas.HospitalManagementSystem.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role_user);
}

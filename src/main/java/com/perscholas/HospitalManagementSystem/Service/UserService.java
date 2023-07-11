package com.perscholas.HospitalManagementSystem.Service;

import com.perscholas.HospitalManagementSystem.Entity.Role;
import com.perscholas.HospitalManagementSystem.Entity.User;
import com.perscholas.HospitalManagementSystem.Exception.DataNotFoundException;
import com.perscholas.HospitalManagementSystem.Exception.UserIdMismatchException;
import com.perscholas.HospitalManagementSystem.Repository.RoleRepository;
import com.perscholas.HospitalManagementSystem.Repository.UserRepository;
import com.perscholas.HospitalManagementSystem.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }


    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findUser(Long id) throws DataNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }
    public User findUserByEmail(String email)throws DataNotFoundException {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) throws DataNotFoundException {
        userRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        userRepository.deleteById(id);
    }
    public User updateUser(User user,Long id)throws UserIdMismatchException, DataNotFoundException {
        if (user.getId() != id) {
            throw new UserIdMismatchException();
        }
        userRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        return userRepository.save(user);
    }
}
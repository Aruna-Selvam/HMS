package com.perscholas.HospitalManagementSystem.controller;

import com.perscholas.HospitalManagementSystem.Entity.User;
import com.perscholas.HospitalManagementSystem.Service.ScheduleAppointmentService;
import com.perscholas.HospitalManagementSystem.Service.UserService;
import com.perscholas.HospitalManagementSystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    final private ScheduleAppointmentService appointmentService;

    @Value("${spring.application.name}")
    String appName;

    public AuthController(UserService userService,
                          ScheduleAppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    // Handler method for displaying the registration page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("user", new UserDto());
        return "register";
    }
    // Handler method for processing user registration
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.createUser(userDto);
        return "redirect:/register?success";
    }}

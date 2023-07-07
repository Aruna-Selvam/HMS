package com.perscholas.HospitalManagementSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class homeController {
    @GetMapping("/")
    public String getHomeController(){
        return "home";
    }
    @GetMapping("/service")
    public String getService(){
        return "service";
    }
    @GetMapping("/schedule")
    public String getSchedule(){
        return "schedule";
    }
    @GetMapping("/signUp")
    public String getSignUp(){
        return "signUp";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }


}
package com.perscholas.HospitalManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/home")
    public String getHomeController(){
        return "home";
    }
    @GetMapping("/")
    public String getLoginController(){
        return "login";
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
    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";
    }


}

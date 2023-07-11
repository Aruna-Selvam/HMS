package com.perscholas.HospitalManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentTimeAlreadyExistsException.class)
    public ResponseEntity<String> handleAppointmentTimeAlreadyExistsException(AppointmentTimeAlreadyExistsException ex) {
        // Customize the response as needed (e.g., return an error message or status code)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        modelAndView.addObject("errorMessage", "An error occurred: " + e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NoPatientsFoundException.class)
    public ModelAndView handleNoPatientsFoundException(NoPatientsFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        modelAndView.addObject("errorMessage", "An error occurred: " + e.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ModelAndView handlePatientNotFoundException(PatientNotFoundException e, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        modelAndView.addObject("errorMessage", "An error occurred: " + e.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ModelAndView handleDataNotFoundException(PatientNotFoundException e, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        modelAndView.addObject("errorMessage", "An error occurred: " + e.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(UserIdMismatchException.class)
    public ModelAndView userIdMismatchException(PatientNotFoundException e, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        modelAndView.addObject("errorMessage", "An error occurred: " + e.getMessage());
        return modelAndView;
    }
}
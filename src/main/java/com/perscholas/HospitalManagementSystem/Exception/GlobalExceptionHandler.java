package com.perscholas.HospitalManagementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentTimeAlreadyExistsException.class)
    public ResponseEntity<String> handleAppointmentTimeAlreadyExistsException(AppointmentTimeAlreadyExistsException ex) {
        // Customize the response as needed (e.g., return an error message or status code)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Add more exception handlers for other custom exceptions or handle other exceptions as needed

}

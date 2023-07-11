package com.perscholas.HospitalManagementSystem.exception;

public class AppointmentTimeAlreadyExistsException extends RuntimeException{
    public AppointmentTimeAlreadyExistsException() {
        super("Appointment time already booked ");
    }

    public AppointmentTimeAlreadyExistsException(String message) {
        super(message);
    }
}

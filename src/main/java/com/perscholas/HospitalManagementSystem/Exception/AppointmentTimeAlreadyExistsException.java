package com.perscholas.HospitalManagementSystem.Exception;

public class AppointmentTimeAlreadyExistsException extends RuntimeException{
    public AppointmentTimeAlreadyExistsException() {
        super("Appointment time already booked ");
    }

    public AppointmentTimeAlreadyExistsException(String message) {
        super(message);
    }
}

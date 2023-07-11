package com.perscholas.HospitalManagementSystem.exception;

public class NoPatientsFoundException extends RuntimeException{
    public NoPatientsFoundException(String message) {
        super(message);
    }
}

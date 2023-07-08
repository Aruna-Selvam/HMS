package com.perscholas.HospitalManagementSystem.Exception;

public class NoPatientsFoundException extends RuntimeException{
    public NoPatientsFoundException(String message) {
        super(message);
    }
}

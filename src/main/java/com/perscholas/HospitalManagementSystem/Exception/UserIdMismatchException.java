package com.perscholas.HospitalManagementSystem.Exception;

public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException() {
        super();
    }

    public UserIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserIdMismatchException(final String message) {
        super(message);
    }

    public UserIdMismatchException(final Throwable cause) {
        super(cause);
    }
}
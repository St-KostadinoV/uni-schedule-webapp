package com.example.unischedulewebapp.exception;

public class TimetableCollisionException extends Exception {
    public TimetableCollisionException() {}
    public TimetableCollisionException(String message) {
        super(message);
    }
}

package com.example.unischedulewebapp.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String message){
        super(message);
    }
}

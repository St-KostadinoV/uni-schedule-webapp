package com.example.unischedulewebapp.exception;

public class ResourceAlreadyExistsException extends Exception{
    public ResourceAlreadyExistsException(){}
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}

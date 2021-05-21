package com.example.unischedulewebapp.exception;

public class BadResourceException extends Exception {
    public BadResourceException(){}
    public BadResourceException(String message){
        super(message);
    }
}
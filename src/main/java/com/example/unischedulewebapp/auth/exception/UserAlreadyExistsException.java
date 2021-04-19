package com.example.unischedulewebapp.auth.exception;

import javax.naming.AuthenticationException;

public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}

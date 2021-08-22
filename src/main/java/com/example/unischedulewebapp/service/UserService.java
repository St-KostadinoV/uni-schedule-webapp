package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.exception.PasswordsMatchException;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.User;
import com.example.unischedulewebapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final static String USER_NOT_FOUND_MSG =
            "User '%s' not found!";
    private final static String USER_EXISTS_MSG =
            "User '%s' already exists!";
    private final static String INVALID_PASSWORD_MSG =
            "The password you entered is incorrect!";

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) throws ResourceNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)
                        ));
    }

    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    String.format(USER_EXISTS_MSG, user.getUsername())
            );
        }

        String encodedPassword = passwordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword, String oldPassword) throws PasswordsMatchException {
        if(!userRepository.existsByUsername(user.getUsername()))
            throw new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, user.getUsername())
            );

        if(!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new PasswordsMatchException(INVALID_PASSWORD_MSG);

        String encodedPassword = passwordEncoder
                .encode(newPassword);

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}

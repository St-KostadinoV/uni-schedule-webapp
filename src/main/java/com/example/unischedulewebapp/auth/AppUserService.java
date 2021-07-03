package com.example.unischedulewebapp.auth;

import com.example.unischedulewebapp.auth.exception.PasswordsMatchException;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User '%s' not found!";
    private final static String USER_EXISTS_MSG =
            "User '%s' already exists!";
    private final static String INVALID_PASSWORD_MSG =
            "The password you entered is incorrect!";

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(PasswordEncoder passwordEncoder,
                          AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG,username)
                        ));
    }

    public void registerUser(AppUser user) throws UserAlreadyExistsException {
        boolean userExists = appUserRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if(userExists)
            throw new UserAlreadyExistsException(
                    String.format(USER_EXISTS_MSG, user.getUsername())
            );

        String encodedPassword = passwordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        appUserRepository.save(user);
    }

    public void updatePassword(AppUser user, String password, String oldPassword) throws PasswordsMatchException {
        boolean userExists = appUserRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if(!userExists)
            throw new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, user.getUsername())
            );

        if(!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new PasswordsMatchException(INVALID_PASSWORD_MSG);

        String encodedPassword = passwordEncoder
                .encode(password);

        user.setPassword(encodedPassword);
        appUserRepository.save(user);
    }
}

package com.example.unischedulewebapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.unischedulewebapp.auth.AppUserRole.TEACHER;
import static com.example.unischedulewebapp.auth.AppUserRole.STUDENT;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - finish app security config
        http
                .csrf().disable()   //  TODO - enable CSRF protection and implement logic to verify CSRF tokens
                .authorizeRequests()
                .mvcMatchers("/api/**/discipline", "/api/**/discipline/**").permitAll()
                .mvcMatchers("/api/**/teacher/daily-schedule").hasRole(TEACHER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}

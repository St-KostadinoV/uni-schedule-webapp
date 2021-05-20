package com.example.unischedulewebapp.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.unischedulewebapp.auth.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - finish app security config
        //      TODO - secure all endpoints
        //      TODO - change default login/logout pages
        http
                .csrf().disable()   //  TODO - enable CSRF protection and implement logic to verify CSRF tokens
                .authorizeRequests()
                .mvcMatchers("/api/v1/teacher/daily-schedule","/api/v1/teacher/weekly-schedule","/api/v1/teacher/email-change","/api/v1/teacher/pass-change").hasRole(TEACHER.name())
                .mvcMatchers("/api/v1/student/daily-schedule","/api/v1/student/weekly-schedule","/api/v1/student/pass-change").hasRole(STUDENT.name())
                .mvcMatchers("/api/v1/management/**").hasRole(FRONT_DESK.name())
                .mvcMatchers("/api/v1/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}

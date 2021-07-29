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
        //      TODO - change default login/logout pages
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/profile/email-change").hasRole(INSTRUCTOR.name())
                .mvcMatchers("/profile/**").hasAnyRole(STUDENT.name(), INSTRUCTOR.name())
                .mvcMatchers("/students/*").hasAnyRole(INSTRUCTOR.name(), FRONT_DESK.name(), EDUCATION_DEPT.name())
                .mvcMatchers("/admin/timetable/**").hasRole(EDUCATION_DEPT.name())
                .mvcMatchers("/admin/**").hasRole(FRONT_DESK.name())
                .mvcMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}

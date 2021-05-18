package com.example.unischedulewebapp.security;

import com.example.unischedulewebapp.auth.AppUserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.unischedulewebapp.auth.AppUserRole.TEACHER;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - finish app security config
            http
                    .authorizeRequests()
                    .antMatchers("/api/**/discipline", "/api/**/discipline/**").permitAll()
                    .antMatchers("/api/teacher/daily-schedule").hasRole(TEACHER.name())
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
    }
}

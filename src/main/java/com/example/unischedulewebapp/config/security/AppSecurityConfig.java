package com.example.unischedulewebapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static com.example.unischedulewebapp.auth.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

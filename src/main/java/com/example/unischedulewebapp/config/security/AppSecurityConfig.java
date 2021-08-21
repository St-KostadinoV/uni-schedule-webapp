package com.example.unischedulewebapp.config.security;

import com.example.unischedulewebapp.auth.jwt.JwtTokenVerifier;
import com.example.unischedulewebapp.auth.jwt.JwtUsernameAndPasswordAuthFilter;
import com.example.unischedulewebapp.config.security.jwt.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.List;

import static com.example.unischedulewebapp.auth.AppUserRole.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public AppSecurityConfig(JwtConfig jwtConfig,
                             SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()
                .mvcMatchers("/profile/email-change").hasRole(INSTRUCTOR.name())
                .mvcMatchers("/profile/**").hasAnyRole(STUDENT.name(), INSTRUCTOR.name())
                .mvcMatchers("/students/*").hasAnyRole(INSTRUCTOR.name(), FRONT_DESK.name(), EDUCATION_DEPT.name())
                .mvcMatchers("/admin/timetable/**").hasRole(EDUCATION_DEPT.name())
                .mvcMatchers("/admin/**").hasRole(FRONT_DESK.name())
                .mvcMatchers("/**").permitAll()
                .anyRequest()
                .authenticated();
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

package com.example.unischedulewebapp;

import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.model.enums.AcademicClassType;
import com.example.unischedulewebapp.model.enums.UserRole;
import com.example.unischedulewebapp.service.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

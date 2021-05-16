package com.example.unischedulewebapp;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserRepository;
import com.example.unischedulewebapp.auth.AppUserRole;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.model.enums.AcademicClassType;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.repository.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRepository userRepository,
										AcademicDepartmentRepository departmentRepository,
										AcademicDisciplineRepository disciplineRepository,
										AcademicFacultyRepository facultyRepository,
										AcademicProgramRepository programRepository,
										AcademicTimetableRepository timetableRepository,
										ProgramDisciplineRepository programDisciplineRepository,
										StudentRepository studentRepository,
										TeacherRepository teacherRepository){
		return args -> {

			AcademicFaculty faculty = new AcademicFaculty("New Faculty", "NF");
			facultyRepository.save(faculty);

			AcademicDepartment department = new AcademicDepartment("New Department",  "ND", faculty);
			departmentRepository.save(department);

			AcademicProgram program = new AcademicProgram("New Program", "NP",  department);
			programRepository.save(program);

			Faker faker = new Faker();
			String fName = faker.name().firstName();
			String mName = faker.name().lastName();
			String lName = faker.name().lastName();
			String email = String.format("%s.%s@tu-varna.bg", fName, lName);
			String phone = faker.phoneNumber().phoneNumber();
			Teacher teacher = new Teacher(
					new AppUser("teacher1", "1234", AppUserRole.TEACHER),
					fName,
					mName,
					lName,
					email,
					phone,
					AcademicTitle.PROFESSOR,
					department,
					"303NF",
					false
			);
			teacherRepository.save(teacher);

			AcademicDiscipline discipline = new AcademicDiscipline("New Discipline", "ND", teacher);
			disciplineRepository.save(discipline);

			fName = faker.name().firstName();
			mName = faker.name().lastName();
			lName = faker.name().lastName();
			email = String.format("%s.%s@tu-varna.bg", fName, lName);
			phone = faker.phoneNumber().phoneNumber();
			String facNum = String.valueOf(faker.number().numberBetween(1000, 9999));
			Student student = new Student(
					new AppUser("student1", "1234", AppUserRole.STUDENT),
					fName,
					mName,
					lName,
					email,
					phone,
					facNum,
					1,
					program,
					1,
					2,
					true
			);
			studentRepository.save(student);

			ProgramDiscipline programDiscipline = new ProgramDiscipline(program,discipline,1);
			programDisciplineRepository.save(programDiscipline);

			LocalTime start = LocalTime.of(9,15);
			LocalTime end = LocalTime.of(11,0);
			AcademicTimetable timetable = new AcademicTimetable(
					teacher,
					DayOfWeek.MONDAY,
					start,
					end,
					"114NF",
					programDiscipline,
					AcademicClassType.LECTURE,
					2
			);
			timetableRepository.save(timetable);
		};
	}
}

package com.example.unischedulewebapp;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserRole;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.model.enums.*;
import com.example.unischedulewebapp.service.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserService userService,
										AcademicDepartmentService  departmentService,
										AcademicDisciplineService disciplineService,
										AcademicFacultyService facultyService,
										AcademicProgramService programService,
										AcademicTimetableService timetableService,
										ProgramDisciplineService programDisciplineService,
										StudentService studentService,
										TeacherService teacherService){
		return args -> {

			AppUser user1 = new AppUser("teacher1", "1234", AppUserRole.TEACHER);
			AppUser user2 = new AppUser("student1", "1234", AppUserRole.STUDENT);

			AcademicFaculty faculty = new AcademicFaculty("New Faculty", "NF");
			facultyService.addFaculty(faculty);

			AcademicDepartment department = new AcademicDepartment("New Department",  "ND", faculty);
			departmentService.addDepartment(department);
			AcademicDepartment department2 = new AcademicDepartment("Another New Department",  "AND", faculty);
			departmentService.addDepartment(department2);

			AcademicProgram program = new AcademicProgram("New Program", "NP",  department);
			programService.addProgram(program);

			Faker faker = new Faker();
			String fName = faker.name().firstName();
			String mName = faker.name().lastName();
			String lName = faker.name().lastName();
			String email = String.format("%s.%s@tu-varna.bg", fName, lName);
			String phone = faker.phoneNumber().phoneNumber();
			Teacher teacher = new Teacher(
					user1,
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
			teacherService.addTeacher(teacher);

			AcademicDiscipline discipline = new AcademicDiscipline("New Discipline", "ND", department, teacher);
			disciplineService.addDiscipline(discipline);
			AcademicDiscipline discipline2 = new AcademicDiscipline("Another New Discipline", "AND", department2, teacher);
			disciplineService.addDiscipline(discipline2);

			fName = faker.name().firstName();
			mName = faker.name().lastName();
			lName = faker.name().lastName();
			email = String.format("%s.%s@tu-varna.bg", fName, lName);
			phone = faker.phoneNumber().phoneNumber();
			String facNum = String.valueOf(faker.number().numberBetween(1000, 9999));
			Student student = new Student(
					user2,
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
			studentService.addStudent(student);

			ProgramDiscipline programDiscipline = new ProgramDiscipline(program,discipline,1);
			programDisciplineService.addProgramDiscipline(programDiscipline);

			LocalTime start = LocalTime.of(9,15);
			LocalTime end = LocalTime.of(11,0);
			AcademicTimetable timetable = new AcademicTimetable(
					teacher,
					DayOfWeek.TUESDAY,
					start,
					end,
					"114NF",
					programDiscipline,
					AcademicClassType.LECTURE,
					2
			);
			timetableService.addTimetable(timetable);
		};
	}
}

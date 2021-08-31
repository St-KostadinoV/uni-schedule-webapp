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

	@Bean
	CommandLineRunner commandLineRunner(AcademicDepartmentService  departmentService,
										AcademicDisciplineService disciplineService,
										AcademicFacultyService facultyService,
										AcademicProgramService programService,
										AcademicTimetableService timetableService,
										ProgramDisciplineService programDisciplineService,
										StudentService studentService,
										InstructorService instructorService,
										UserService userService){
		return args -> {
//			List<AcademicTimetable> timetables_SIT_3 = List.of(
//					new AcademicTimetable(
//							instructorService.findById(94L),
//							DayOfWeek.TUESDAY,
//							LocalTime.of(9,15),
//							LocalTime.of(11, 0),
//							"212Е",
//							programDisciplineService.findById(7L),	// diskretni
//							AcademicClassType.LECTURE
//					),
//					new AcademicTimetable(
//							instructorService.findById(146L),
//							DayOfWeek.WEDNESDAY,
//							LocalTime.of(9,15),
//							LocalTime.of(11, 0),
//							"212Е",
//							programDisciplineService.findById(8L),	// bazi danni
//							AcademicClassType.LECTURE
//					),
//					new AcademicTimetable(
//							instructorService.findById(90L),
//							DayOfWeek.TUESDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"212Е",
//							programDisciplineService.findById(9L),	// oop1
//							AcademicClassType.LECTURE
//					),
//					new AcademicTimetable(
//							instructorService.findById(92L),
//							DayOfWeek.WEDNESDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"212Е",
//							programDisciplineService.findById(10L),	// organizaciq
//							AcademicClassType.LECTURE
//					),
//					new AcademicTimetable(
//							instructorService.findById(144L),
//							DayOfWeek.TUESDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"114M",
//							programDisciplineService.findById(11L),	// sistemen analiz
//							AcademicClassType.LECTURE
//					),
//					new AcademicTimetable(
//							instructorService.findById(94L),
//							DayOfWeek.WEDNESDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"231НУК",
//							programDisciplineService.findById(7L),	// diskretni
//							AcademicClassType.LAB_TUTORIAL,
//							1
//					),
//					new AcademicTimetable(
//							instructorService.findById(157L),
//							DayOfWeek.THURSDAY,
//							LocalTime.of(9,15),
//							LocalTime.of(11, 0),
//							"305ТВ",
//							programDisciplineService.findById(8L),	// bazi danni
//							AcademicClassType.LAB_TUTORIAL,
//							1
//					),
//					new AcademicTimetable(
//							instructorService.findById(90L),
//							DayOfWeek.THURSDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"407ТВ",
//							programDisciplineService.findById(9L),	// oop1
//							AcademicClassType.LAB_TUTORIAL,
//							1
//					),
//					new AcademicTimetable(
//							instructorService.findById(92L),
//							DayOfWeek.FRIDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"305ТВ",
//							programDisciplineService.findById(10L),	// organizaciq
//							AcademicClassType.LAB_TUTORIAL,
//							1
//					),
//					new AcademicTimetable(
//							instructorService.findById(154L),
//							DayOfWeek.FRIDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"405ТВ",
//							programDisciplineService.findById(11L),	// sistemen analiz
//							AcademicClassType.LAB_TUTORIAL,
//							1
//					),
//					new AcademicTimetable(
//							instructorService.findById(94L),
//							DayOfWeek.FRIDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"333НУК",
//							programDisciplineService.findById(7L),	// diskretni
//							AcademicClassType.LAB_TUTORIAL,
//							2
//					),
//					new AcademicTimetable(
//							instructorService.findById(146L),
//							DayOfWeek.FRIDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"306ТВ",
//							programDisciplineService.findById(8L),	// bazi danni
//							AcademicClassType.LAB_TUTORIAL,
//							2
//					),
//					new AcademicTimetable(
//							instructorService.findById(90L),
//							DayOfWeek.WEDNESDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"302ТВ",
//							programDisciplineService.findById(9L),	// oop1
//							AcademicClassType.LAB_TUTORIAL,
//							2
//					),
//					new AcademicTimetable(
//							instructorService.findById(92L),
//							DayOfWeek.THURSDAY,
//							LocalTime.of(13,15),
//							LocalTime.of(15, 0),
//							"403ТВ",
//							programDisciplineService.findById(10L),	// organizaciq
//							AcademicClassType.LAB_TUTORIAL,
//							2
//					),
//					new AcademicTimetable(
//							instructorService.findById(154L),
//							DayOfWeek.THURSDAY,
//							LocalTime.of(11,15),
//							LocalTime.of(13, 0),
//							"211ТВ",
//							programDisciplineService.findById(11L),	// sistemen analiz
//							AcademicClassType.LAB_TUTORIAL,
//							2
//					)
//			);
//
//			for (AcademicTimetable timetable : timetables_SIT_3)
//				timetableService.addTimetable(timetable);
		};
	}
}

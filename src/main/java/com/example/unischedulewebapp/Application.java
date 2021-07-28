package com.example.unischedulewebapp;

import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
										InstructorService instructorService){
		return args -> {

			List<AcademicFaculty> faculties = List.of(
					new AcademicFaculty("Машшино-технологичен факултет", "МФ"),
					new AcademicFaculty("Корабостроителен факултет", "КФ"),
					new AcademicFaculty("Електротехнически факултет", "ЕФ"),
					new AcademicFaculty("Факултет по изчислителна техника и автоматизация", "ФИТА"),
					new AcademicFaculty("Добруджански технологичен колеж",  "ДТК"),
					new AcademicFaculty("Колеж в структурата на ТУ-Варна",  "КТУ"),
					new AcademicFaculty("Департамент по езиково и продължаващо обучние и спорт",  "ДЕПОС")
			);
			for (AcademicFaculty faculty : faculties)
				facultyService.addFaculty(faculty);

			List<AcademicDepartment> departments = List.of(
					new AcademicDepartment("Автоматизация на производството",  "АП", faculties.get(3)),
					new AcademicDepartment("Добруджански технологичен колеж",  "ДТК", faculties.get(4)),
					new AcademicDepartment("Екология и опазване на околната среда",  "ЕООС", faculties.get(1)),
					new AcademicDepartment("Електроенергетика",  "ЕЕ", faculties.get(2)),
					new AcademicDepartment("Електроснабдяване и електрообзавеждане",  "ЕСЕО", faculties.get(2)),
					new AcademicDepartment("Електротехника и електротехнологии",  "ЕТЕТ", faculties.get(2)),
					new AcademicDepartment("Индустриален дизайн",  "ИД", faculties.get(1)),
					new AcademicDepartment("Индустриален мениджмънт",  "ИМ", faculties.get(0)),
					new AcademicDepartment("Компютърни науки и технологии",  "КНТ", faculties.get(3)),
					new AcademicDepartment("Комуникационна техника и технологии",  "КТТ", faculties.get(3)),
					new AcademicDepartment("Корабни машини и механизми",  "КММ", faculties.get(1)),
					new AcademicDepartment("Корабоводене, управление на транспорта и опазване чистотата на водните пътища",  "КУТОЧВП", faculties.get(1)),
					new AcademicDepartment("Корабостроене",  "К", faculties.get(1)),
					new AcademicDepartment("Корабостроене и корабни машини и механизми",  "ККММ", faculties.get(1)),
					new AcademicDepartment("Колеж в структурата на ТУ-Варна",  "КТУ", faculties.get(5)),
					new AcademicDepartment("Математика и физика",  "МФ", faculties.get(2)),
					new AcademicDepartment("Материалознание и технология на материалите",  "МТМ", faculties.get(0)),
					new AcademicDepartment("Механика и машинни елементи",  "ММЕ", faculties.get(0)),
					new AcademicDepartment("Общоуниверситетска работилница",  "ОУР", faculties.get(0)),
					new AcademicDepartment("Растениевъдство",  "Р", faculties.get(0)),
					new AcademicDepartment("Секция 'езиково обучение'",  "ЕО", faculties.get(6)),
					new AcademicDepartment("Секция 'математика'",  "М", faculties.get(2)),
					new AcademicDepartment("Секция 'физика'",  "Ф", faculties.get(2)),
					new AcademicDepartment("Секция 'физическо възпитание и спорт'",  "ФВС", faculties.get(6)),
					new AcademicDepartment("Софтуерни и интернет технологии",  "СИТ", faculties.get(3)),
					new AcademicDepartment("Социални и правни науки",  "СПН", faculties.get(2)),
					new AcademicDepartment("Теоретична и измервателна електротехника",  "ТИЕ", faculties.get(2)),
					new AcademicDepartment("Технология на машиностроенето и металорежещи машини",  "ТМММ", faculties.get(0)),
					new AcademicDepartment("Топлотехника",  "ТТ", faculties.get(1)),
					new AcademicDepartment("Транспортна техника и технологии",  "ТТТ", faculties.get(0))
			);
			for (AcademicDepartment department : departments)
				departmentService.addDepartment(department);
//
//			List<AcademicProgram> programs = List.of(
//					new AcademicProgram("Компютъризирани технологии в машиностроенето", "КТМ", departments.get(0), 1),
//					new AcademicProgram("Производствен инженеринг", "ПИ", departments.get(0), 1),
//					new AcademicProgram("Машиностроителна техника и технологии", "МТТ", departments.get(1), 1),
//					new AcademicProgram("Химическо машиностроене", "ХМ", departments.get(1), 1),
//					new AcademicProgram("Индустриален мениджмънт", "ИМ", departments.get(2), 7),
//					new AcademicProgram("Технологично предприемачество и иновации", "ТПИ", departments.get(2), 7),
//					new AcademicProgram("Корабостроене и морска техника", "КМТ", departments.get(3), 2),
//					new AcademicProgram("Корабни машини и механизми", "КММ", departments.get(3), 2),
//					new AcademicProgram("Инженерна екология", "ИЕ", departments.get(4), 7),
//					new AcademicProgram("Защита на населението при бедствия и аварии", "ЗНБА",  departments.get(4), 7),
//					new AcademicProgram("Индустриален дизайн", "ИД",  departments.get(5), 1),
//					new AcademicProgram("Електротехника и електротехнологии", "ЕТЕТ", departments.get(6), 3),
//					new AcademicProgram("Възобновяеми енергийни източници", "ВЕИ", departments.get(6), 3),
//					new AcademicProgram("Компютърни системи и технологии", "КСТ", departments.get(8), 5),
//					new AcademicProgram("Софтуерни и интернет технологии", "СИТ", departments.get(9), 6)
//			);
//			for (AcademicProgram program : programs)
//				programService.addProgram(program);
//
//			Faker faker = new Faker();
//			Random random = new Random();
//			AppUser user;String fName;String mName;String lName;String email;String phone;
//
//			List<Teacher> teachers = new ArrayList<>();
//			Teacher teacher;
//			for (int i = 0; i < 20; i++){
//				fName = faker.name().firstName();
//				mName = faker.name().lastName();
//				lName = faker.name().lastName();
//				email = String.format("%s.%s@tu-varna.bg", fName, lName);
//				phone = faker.phoneNumber().phoneNumber();
//				user = new AppUser("TEACHER_" + (i + 1), "1234", AppUserRole.TEACHER);
//				teacher = new Teacher(
//						user,
//						fName,
//						mName,
//						lName,
//						email,
//						phone,
//						AcademicTitle.PROFESSOR,
//						departments.get(random.nextInt(10)),
//						(random.nextInt(500-100)+100) + faculties.get(random.nextInt(4)).getAbbreviation(),
//						false
//				);
//				teachers.add(teacher);
//				teacherService.addTeacher(teacher);
//			}
//
//			List<AcademicDiscipline> disciplines = new ArrayList<>();
//			AcademicDiscipline discipline;
//			for(int i = 0; i<30; i++) {
//				discipline = new AcademicDiscipline("Discipline#" + (100 + i), "D#" + (100 + i), departments.get(random.nextInt(10)), teachers.get(random.nextInt(20)));
//				disciplines.add(discipline);
//				disciplineService.addDiscipline(discipline);
//			}
//
//			AcademicDiscipline test = new AcademicDiscipline("Discipline#999", "D#999", departments.get(0), teachers.get(0));
//			test.addAssistingTeachers(teachers.get(1));
//			disciplineService.addDiscipline(test);
//
//			List<Student> students = new ArrayList<>();
//			Student student;
//			String facNum;
//			for(int i = 0; i<10; i++){
//				fName = faker.name().firstName();
//				mName = faker.name().lastName();
//				lName = faker.name().lastName();
//				email = String.format("%s.%s@tu-varna.bg", fName, lName);
//				phone = faker.phoneNumber().phoneNumber();
//				facNum = "FN3" + (100+i);
//				user = new AppUser("STUDENT_1_1_" + (i+1), "1234", AppUserRole.STUDENT);
//				student = new Student(
//						user,
//						fName,
//						mName,
//						lName,
//						email,
//						phone,
//						facNum,
//						programs.get(random.nextInt(15)),
//						1,
//						1,
//						true
//				);
//				students.add(student);
//				studentService.addStudent(student);
//			}
//			for(int i = 0; i<10; i++){
//				fName = faker.name().firstName();
//				mName = faker.name().lastName();
//				lName = faker.name().lastName();
//				email = String.format("%s.%s@tu-varna.bg", fName, lName);
//				phone = faker.phoneNumber().phoneNumber();
//				facNum = "FN4" + (100+i);
//				user = new AppUser("STUDENT_1_2_" + (i+1), "1234", AppUserRole.STUDENT);
//				student = new Student(
//						user,
//						fName,
//						mName,
//						lName,
//						email,
//						phone,
//						facNum,
//						programs.get(random.nextInt(15)),
//						1,
//						2,
//						true
//				);
//				students.add(student);
//				studentService.addStudent(student);
//			}
//			for(int i = 0; i<10; i++){
//				fName = faker.name().firstName();
//				mName = faker.name().lastName();
//				lName = faker.name().lastName();
//				email = String.format("%s.%s@tu-varna.bg", fName, lName);
//				phone = faker.phoneNumber().phoneNumber();
//				facNum = "FN1" + (100+i);
//				user = new AppUser("STUDENT_2_1_" + (i+1), "1234", AppUserRole.STUDENT);
//				student = new Student(
//						user,
//						fName,
//						mName,
//						lName,
//						email,
//						phone,
//						facNum,
//						programs.get(random.nextInt(15)),
//						2,
//						1,
//						true
//				);
//				students.add(student);
//				studentService.addStudent(student);
//			}
//			for(int i = 0; i<10; i++){
//				fName = faker.name().firstName();
//				mName = faker.name().lastName();
//				lName = faker.name().lastName();
//				email = String.format("%s.%s@tu-varna.bg", fName, lName);
//				phone = faker.phoneNumber().phoneNumber();
//				facNum = "FN2" + (100+i);
//				user = new AppUser("STUDENT_2_2_" + (i+1), "1234", AppUserRole.STUDENT);
//				student = new Student(
//						user,
//						fName,
//						mName,
//						lName,
//						email,
//						phone,
//						facNum,
//						programs.get(random.nextInt(15)),
//						2,
//						2,
//						true
//				);
//				students.add(student);
//				studentService.addStudent(student);
//			}
//
//			List<ProgramDiscipline> programDisciplines = List.of(
//					new ProgramDiscipline(programs.get(0), disciplines.get(0),1),
//					new ProgramDiscipline(programs.get(0), disciplines.get(2),1),
//					new ProgramDiscipline(programs.get(1), disciplines.get(2),1),
//					new ProgramDiscipline(programs.get(1), disciplines.get(4),1),
//					new ProgramDiscipline(programs.get(2), disciplines.get(4),1),
//					new ProgramDiscipline(programs.get(2), disciplines.get(6),1),
//					new ProgramDiscipline(programs.get(3), disciplines.get(6),1),
//					new ProgramDiscipline(programs.get(3), disciplines.get(8),1),
//					new ProgramDiscipline(programs.get(4), disciplines.get(8),1),
//					new ProgramDiscipline(programs.get(4), disciplines.get(10),1),
//					new ProgramDiscipline(programs.get(5), disciplines.get(10),1),
//					new ProgramDiscipline(programs.get(5), disciplines.get(12),1),
//					new ProgramDiscipline(programs.get(6), disciplines.get(12),1),
//					new ProgramDiscipline(programs.get(6), disciplines.get(14),1),
//					new ProgramDiscipline(programs.get(7), disciplines.get(14),1),
//					new ProgramDiscipline(programs.get(7), disciplines.get(16),1),
//					new ProgramDiscipline(programs.get(8), disciplines.get(16),1),
//					new ProgramDiscipline(programs.get(8), disciplines.get(18),1),
//					new ProgramDiscipline(programs.get(9), disciplines.get(18),1),
//					new ProgramDiscipline(programs.get(9), disciplines.get(20),1),
//					new ProgramDiscipline(programs.get(10), disciplines.get(20),1),
//					new ProgramDiscipline(programs.get(10), disciplines.get(22),1),
//					new ProgramDiscipline(programs.get(11), disciplines.get(22),1),
//					new ProgramDiscipline(programs.get(11), disciplines.get(24),1),
//					new ProgramDiscipline(programs.get(12), disciplines.get(24),1),
//					new ProgramDiscipline(programs.get(12), disciplines.get(26),1),
//					new ProgramDiscipline(programs.get(13), disciplines.get(26),1),
//					new ProgramDiscipline(programs.get(13), disciplines.get(28),1),
//					new ProgramDiscipline(programs.get(14), disciplines.get(28),1),
//					new ProgramDiscipline(programs.get(14), disciplines.get(0),1),
//					new ProgramDiscipline(programs.get(0), disciplines.get(1),2),
//					new ProgramDiscipline(programs.get(0), disciplines.get(3),2),
//					new ProgramDiscipline(programs.get(1), disciplines.get(3),2),
//					new ProgramDiscipline(programs.get(1), disciplines.get(5),2),
//					new ProgramDiscipline(programs.get(2), disciplines.get(5),2),
//					new ProgramDiscipline(programs.get(2), disciplines.get(7),2),
//					new ProgramDiscipline(programs.get(3), disciplines.get(7),2),
//					new ProgramDiscipline(programs.get(3), disciplines.get(9),2),
//					new ProgramDiscipline(programs.get(4), disciplines.get(9),2),
//					new ProgramDiscipline(programs.get(4), disciplines.get(11),2),
//					new ProgramDiscipline(programs.get(5), disciplines.get(11),2),
//					new ProgramDiscipline(programs.get(5), disciplines.get(13),2),
//					new ProgramDiscipline(programs.get(6), disciplines.get(13),2),
//					new ProgramDiscipline(programs.get(6), disciplines.get(15),2),
//					new ProgramDiscipline(programs.get(7), disciplines.get(15),2),
//					new ProgramDiscipline(programs.get(7), disciplines.get(17),2),
//					new ProgramDiscipline(programs.get(8), disciplines.get(17),2),
//					new ProgramDiscipline(programs.get(8), disciplines.get(19),2),
//					new ProgramDiscipline(programs.get(9), disciplines.get(19),2),
//					new ProgramDiscipline(programs.get(9), disciplines.get(21),2),
//					new ProgramDiscipline(programs.get(10), disciplines.get(21),2),
//					new ProgramDiscipline(programs.get(10), disciplines.get(23),2),
//					new ProgramDiscipline(programs.get(11), disciplines.get(23),2),
//					new ProgramDiscipline(programs.get(11), disciplines.get(25),2),
//					new ProgramDiscipline(programs.get(12), disciplines.get(25),2),
//					new ProgramDiscipline(programs.get(12), disciplines.get(27),2),
//					new ProgramDiscipline(programs.get(13), disciplines.get(27),2),
//					new ProgramDiscipline(programs.get(13), disciplines.get(29),2),
//					new ProgramDiscipline(programs.get(14), disciplines.get(29),2),
//					new ProgramDiscipline(programs.get(14), disciplines.get(1),2)
//			);
//			for (ProgramDiscipline programDiscipline : programDisciplines)
//				programDisciplineService.addProgramDiscipline(programDiscipline);
//
//			LocalTime start;
//			AcademicTimetable timetable;
//			for(int i = 0; i<3; i++) {
//				start = LocalTime.of((i*2)+9,15);
//				timetable = new AcademicTimetable(
//						teachers.get(random.nextInt(20)),
//						DayOfWeek.MONDAY,
//						start,
//						LocalTime.of(start.getHour() + 2,0),
//						"114NF",
//						programDisciplines.get(random.nextInt(programDisciplines.size())),
//						AcademicClassType.LECTURE,
//						random.nextInt(2) + 1
//				);
//				timetableService.addTimetable(timetable);
//			}
//			for(int i = 0; i<3; i++) {
//				start = LocalTime.of((i*2)+9,15);
//				timetable = new AcademicTimetable(
//						teachers.get(random.nextInt(20)),
//						DayOfWeek.TUESDAY,
//						start,
//						LocalTime.of(start.getHour() + 2,0),
//						"114NF",
//						programDisciplines.get(random.nextInt(programDisciplines.size())),
//						AcademicClassType.LECTURE,
//						random.nextInt(2) + 1
//				);
//				timetableService.addTimetable(timetable);
//			}
//			for(int i = 0; i<3; i++) {
//				start = LocalTime.of((i*2)+9,15);
//				timetable = new AcademicTimetable(
//						teachers.get(random.nextInt(20)),
//						DayOfWeek.WEDNESDAY,
//						start,
//						LocalTime.of(start.getHour() + 2,0),
//						"114NF",
//						programDisciplines.get(random.nextInt(programDisciplines.size())),
//						AcademicClassType.LECTURE,
//						random.nextInt(2) + 1
//				);
//				timetableService.addTimetable(timetable);
//			}
//			for(int i = 0; i<3; i++) {
//				start = LocalTime.of((i*2)+9,15);
//				timetable = new AcademicTimetable(
//						teachers.get(random.nextInt(20)),
//						DayOfWeek.THURSDAY,
//						start,
//						LocalTime.of(start.getHour() + 2,0),
//						"114NF",
//						programDisciplines.get(random.nextInt(programDisciplines.size())),
//						AcademicClassType.LECTURE,
//						random.nextInt(2) + 1
//				);
//				timetableService.addTimetable(timetable);
//			}
//			for(int i = 0; i<3; i++) {
//				start = LocalTime.of((i*2)+9,15);
//				timetable = new AcademicTimetable(
//						teachers.get(random.nextInt(20)),
//						DayOfWeek.FRIDAY,
//						start,
//						LocalTime.of(start.getHour() + 2,0),
//						"114NF",
//						programDisciplines.get(random.nextInt(programDisciplines.size())),
//						AcademicClassType.LECTURE,
//						random.nextInt(2) + 1
//				);
//				timetableService.addTimetable(timetable);
//			}
//
//			user = new AppUser("FRONT","1234",AppUserRole.FRONT_DESK);
//			userService.registerUser(user);
//			System.out.println(LocalDateTime.now() + "\ttesting input data inserted");
		};
	}
}

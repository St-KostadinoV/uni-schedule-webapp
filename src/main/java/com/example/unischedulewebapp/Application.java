package com.example.unischedulewebapp;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static com.example.unischedulewebapp.auth.AppUserRole.STUDENT;
import static com.example.unischedulewebapp.auth.AppUserRole.INSTRUCTOR;
import static com.example.unischedulewebapp.model.enums.AcademicDegree.*;
import static com.example.unischedulewebapp.model.enums.AcademicTitle.*;

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
					new AcademicDepartment("Автоматизация на производството",  "АП", faculties.get(3)),															//0
					new AcademicDepartment("Добруджански технологичен колеж",  "ДТК", faculties.get(4)),
					new AcademicDepartment("Екология и опазване на околната среда",  "ЕООС", faculties.get(1)),
					new AcademicDepartment("Електроенергетика",  "ЕЕ", faculties.get(2)),
					new AcademicDepartment("Електронна техника и микроелектроника",  "ЕТМ", faculties.get(3)),
					new AcademicDepartment("Електроснабдяване и електрообзавеждане",  "ЕСЕО", faculties.get(2)),												//5
					new AcademicDepartment("Електротехника и електротехнологии",  "ЕТЕТ", faculties.get(2)),
					new AcademicDepartment("Индустриален дизайн",  "ИД", faculties.get(1)),
					new AcademicDepartment("Индустриален мениджмънт",  "ИМ", faculties.get(0)),
					new AcademicDepartment("Компютърни науки и технологии",  "КНТ", faculties.get(3)),
					new AcademicDepartment("Комуникационна техника и технологии",  "КТТ", faculties.get(3)),													//10
					new AcademicDepartment("Корабни машини и механизми",  "КММ", faculties.get(1)),
					new AcademicDepartment("Корабоводене, управление на транспорта и опазване чистотата на водните пътища",  "КУТОЧВП", faculties.get(1)),
					new AcademicDepartment("Корабостроене",  "К", faculties.get(1)),
					new AcademicDepartment("Корабостроене, корабни машини и механизми",  "ККММ", faculties.get(1)),
					new AcademicDepartment("Колеж в структурата на ТУ-Варна",  "КТУ", faculties.get(5)),														//15
					new AcademicDepartment("Математика и физика",  "МФ", faculties.get(2)),
					new AcademicDepartment("Материалознание и технология на материалите",  "МТМ", faculties.get(0)),
					new AcademicDepartment("Механика и машинни елементи",  "ММЕ", faculties.get(0)),
					new AcademicDepartment("Общоуниверситетска работилница",  "ОУР", faculties.get(0)),
					new AcademicDepartment("Растениевъдство",  "Р", faculties.get(0)),																			//20
					new AcademicDepartment("Секция 'езиково обучение'",  "ЕО", faculties.get(6)),
					new AcademicDepartment("Секция 'математика'",  "М", faculties.get(2)),
					new AcademicDepartment("Секция 'физика'",  "Ф", faculties.get(2)),
					new AcademicDepartment("Секция 'физическо възпитание и спорт'",  "ФВС", faculties.get(6)),
					new AcademicDepartment("Софтуерни и интернет технологии",  "СИТ", faculties.get(3)),														//25
					new AcademicDepartment("Социални и правни науки",  "СПН", faculties.get(2)),
					new AcademicDepartment("Теоретична и измервателна електротехника",  "ТИЕ", faculties.get(2)),
					new AcademicDepartment("Технология на машиностроенето и металорежещи машини",  "ТМММ", faculties.get(0)),
					new AcademicDepartment("Топлотехника",  "ТТ", faculties.get(1)),
					new AcademicDepartment("Транспортна техника и технологии",  "ТТТ", faculties.get(0))														//30
			);
			for (AcademicDepartment department : departments)
				departmentService.addDepartment(department);

			List<AcademicProgram> bachelorPrograms = List.of(
					new AcademicProgram("Автоматика, информационни и управляващи коммпютърни системи", "АИУКС", departments.get(0), 0, BACHELORS, 4f),
					new AcademicProgram("Автомобилна техника", "АТ", departments.get(30), 0, BACHELORS, 4f),
					new AcademicProgram("Агрономство", "А", departments.get(19), 0, BACHELORS, 4f),
					new AcademicProgram("Биомедицинска електроника", "БМЕ", departments.get(4), 0, BACHELORS, 4f),
					new AcademicProgram("Възобновяеми енергийни източници", "ВЕИ", departments.get(6), 0, BACHELORS, 4f),
					new AcademicProgram("Електроенергетика", "ЕЕ", departments.get(3), 0, BACHELORS, 4f),
					new AcademicProgram("Електроника", "Е", departments.get(4), 0, BACHELORS, 4f),
					new AcademicProgram("Електрообзавейдане на кораба", "ЕОК", departments.get(5), 0, BACHELORS, 4f),
					new AcademicProgram("Електроснабдяване и електрообзавеждане", "ЕСЕО", departments.get(5), 0, BACHELORS, 4f),
					new AcademicProgram("Електротехника и електротехнологии", "ЕТЕТ", departments.get(6), 0, BACHELORS, 4f),
					new AcademicProgram("Защита на населението при бедствия и аварии", "ЗНБА", departments.get(2), 0, BACHELORS, 4f),
					new AcademicProgram("Индустриален дизайн", "ИД", departments.get(7), 0, BACHELORS, 4f),
					new AcademicProgram("Индустриален мениджмънт", "ИМ", departments.get(8), 0, BACHELORS, 4f),
					new AcademicProgram("Инженерна екология", "ИЕ", departments.get(2), 0, BACHELORS, 4f),
					new AcademicProgram("Информационни и комуникационн технологии", "ИКТ", departments.get(10), 0, BACHELORS, 4f),
					new AcademicProgram("Компютъризирани технологии в машиностроенето", "КТМ", departments.get(28), 0, BACHELORS, 4f),
					new AcademicProgram("Компютърни системи и технологии", "КСТ", departments.get(9), 0, BACHELORS, 4f),
					new AcademicProgram("Корабни машини и механизми", "КММ", departments.get(14), 0, BACHELORS, 4f),
					new AcademicProgram("Корабоводене", "КВ", departments.get(12), 0, BACHELORS, 4f),
					new AcademicProgram("Корабостроене и морска техника", "КМТ", departments.get(14), 0, BACHELORS, 4f),
					new AcademicProgram("Логистика на водния транспорт", "ЛВТ", departments.get(12), 0, BACHELORS, 4f),
					new AcademicProgram("Машиностроителна техника и технологии", "МТТ", departments.get(17), 0, BACHELORS, 4f),
					new AcademicProgram("Производствен инженеринг", "ПИ", departments.get(28), 0, BACHELORS, 4f),
					new AcademicProgram("Роботика и мехатроника", "РМ", departments.get(0), 0, BACHELORS, 4f),
					new AcademicProgram("Софтуерни и интернет технологии", "СИТ", departments.get(25), 0, BACHELORS, 4f),
					new AcademicProgram("Социален мениджмънт", "СМ", departments.get(26), 0, BACHELORS, 4f),
					new AcademicProgram("Технологично предприемачество и иновации", "ТПИ", departments.get(8), 0, BACHELORS, 4f),
					new AcademicProgram("Топлотехника и инвестиционно проектиране", "ТИП", departments.get(29), 0, BACHELORS, 4f),
					new AcademicProgram("Транспортна техника и технологии", "ТТТ", departments.get(30), 0, BACHELORS, 4f)
			);
			for (AcademicProgram program : bachelorPrograms)
				programService.addProgram(program);

			List<Instructor> instructorsAP = List.of(
					new Instructor(
							new AppUser("INSTR_AP_1", "1234", INSTRUCTOR),
							"Никола",
							"Николаев",
							"Николов",
							"nn_nikolov@tu-varna.bg",
							"052/383 329, 052/383 312",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"812Е, 410Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_2", "1234", INSTRUCTOR),
							"Марияна",
							"Георгиева",
							"Тодорова",
							"mgtodorova@tu-varna.bg",
							"052/383 205",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"303аЕ, 826Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_3", "1234", INSTRUCTOR),
							"Наско",
							"Райчев",
							"Атанасов",
							"nasko_ratanasov@yahoo.com",
							"052/383 397",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"829Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_4", "1234", INSTRUCTOR),
							"Мариела",
							"Иванова",
							"Александрова",
							"m_alexandrova@tu-varna.bg",
							"052/383 312",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"410аЕ, 410Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_5", "1234", INSTRUCTOR),
							"Веско",
							"Христов",
							"Узунов",
							"vuzunov@tu-varna.bg",
							"052/383 554",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"525НУК, 529НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_6", "1234", INSTRUCTOR),
							"Живко",
							"Стефков",
							"Жеков",
							"zhivkо_zhekov@tu-varna.bg",
							"052/383 395",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(0),
							"211Е, 713Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_7", "1234", INSTRUCTOR),
							"Диан",
							"Богданов",
							"Джибаров",
							"djibarov@tu-varna.bg",
							"052/383 554",
							ASSISTANT_PROFESSOR,
							departments.get(0),
							"525НУК, 529НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_8", "1234", INSTRUCTOR),
							"Елена",
							"Драгомирова",
							"Драганова",
							"elena.draganova@tu-varna.bg",
							"052/383 465",
							ASSISTANT_PROFESSOR,
							departments.get(0),
							"520НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_9", "1234", INSTRUCTOR),
							"Ренета",
							"Данчева",
							"Първанова",
							"",
							"052/383 205",
							ASSISTANT_PROFESSOR,
							departments.get(0),
							"826Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_10", "1234", INSTRUCTOR),
							"Иван",
							"Веселинов",
							"Григоров",
							"ivan_grigorov@tu-varna.bg",
							"052/383 397",
							ASSISTANT_PROFESSOR,
							departments.get(0),
							"829Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_AP_11", "1234", INSTRUCTOR),
							"Емил",
							"Йорданов",
							"Маринов",
							"e_marinov@tu-varna.bg",
							"",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"812Е",
							true
					),
					new Instructor(
							new AppUser("INSTR_AP_12", "1234", INSTRUCTOR),
							"Велко",
							"Георгиев",
							"Наумов",
							"velko.naumov@tu-varna.bg",
							"052/303-090, 052/383-422",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"327НУК, 512НУК",
							true
					),
					new Instructor(
							new AppUser("INSTR_AP_13", "1234", INSTRUCTOR),
							"Янко",
							"Стоянов",
							"Янев",
							"yayanev@tu-varna.bg",
							"052/383-465",
							ASSOCIATE_PROFESSOR,
							departments.get(0),
							"521НУК",
							true
					),
					new Instructor(
							new AppUser("INSTR_AP_14", "1234", INSTRUCTOR),
							"Пламен",
							"Антонов",
							"Манасиев",
							"pamap@tu-varna.bg",
							"(052) 383-460",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(0),
							"309УПБ, 114УПБ",
							true
					),
					new Instructor(
							new AppUser("INSTR_AP_15", "1234", INSTRUCTOR),
							"Светлана",
							"Георгиева",
							"Герганова-Спасова",
							"",
							"052/383 397",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(0),
							"",
							true
					)
			);

			List<Instructor> instructorsDTK = List.of(
					new Instructor(
							new AppUser("INSTR_DTK_1", "1234", INSTRUCTOR),
							"Свилен",
							"Христов",
							"Стоянов",
							"svilenh@tu-varna.bg",
							"0894612364",
							ASSOCIATE_PROFESSOR,
							departments.get(1),
							"ДТК 406",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_2", "1234", INSTRUCTOR),
							"Радко",
							"Петров",
							"Михайлов",
							"rmihajlow@tu-varna.bg",
							"058 604712",
							ASSOCIATE_PROFESSOR,
							departments.get(1),
							"ДТК 501",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_3", "1234", INSTRUCTOR),
							"Владимир",
							"Георгиев",
							"Демирев",
							"vl.demirev@tu-varna.bg",
							"0894651789",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(1),
							"ДТК 504",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_4", "1234", INSTRUCTOR),
							"Светлозар",
							"Кирилов",
							"Захариев",
							"szahariev@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(1),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_5", "1234", INSTRUCTOR),
							"Светлана",
							"Михайлова",
							"Паскалева",
							"sv_paskaleva@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(1),
							"ДТК 403",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_6", "1234", INSTRUCTOR),
							"Красимира",
							"Петкова",
							"Загорова",
							"kzagorova@tu-varna.bg",
							"0894642305",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(1),
							"ДТК 407-1",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_7", "1234", INSTRUCTOR),
							"Десислава",
							"Палчева",
							"Михайлова",
							"desislava.mihaylova@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(1),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_DTK_8", "1234", INSTRUCTOR),
							"Аспарух",
							"Иванов",
							"Атанасов",
							"asparuh.atanasov@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(1),
							"",
							false
					)
			);

			List<Instructor> instructorsEOOS = List.of(
					new Instructor(
							new AppUser("INSTR_EOOS_1", "1234", INSTRUCTOR),
							"Даниела",
							"Симеонова",
							"Тонева",
							"d_toneva@tu-varna.bg",
							"052/383 664",
							ASSOCIATE_PROFESSOR,
							departments.get(2),
							"304НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_2", "1234", INSTRUCTOR),
							"Николай",
							"Николаев",
							"Минчев",
							"nnminchev@tu-varna.bg",
							"052/383 509",
							ASSOCIATE_PROFESSOR,
							departments.get(2),
							"311НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_3", "1234", INSTRUCTOR),
							"Анна",
							"Костадинова",
							"Симеонова",
							"annsim@tu-varna.bg",
							"052/383-272",
							ASSOCIATE_PROFESSOR,
							departments.get(2),
							"318НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_4", "1234", INSTRUCTOR),
							"Стоян",
							"Иванов",
							"Вергиев",
							"stvergiev@tu-varna.bg",
							"052/383-509",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(2),
							"303НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_5", "1234", INSTRUCTOR),
							"Татяна",
							"Любенова",
							"Жекова",
							"tatianazhekova@tu-varna.bg",
							"052/383-449",
							ASSISTANT_PROFESSOR,
							departments.get(2),
							"317аНУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_6", "1234", INSTRUCTOR),
							"Стефан",
							"Колев",
							"Колев",
							"kolevskk@tu-varna.bg",
							"052/383-653",
							ASSISTANT_PROFESSOR,
							departments.get(2),
							"309НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_7", "1234", INSTRUCTOR),
							"Елена",
							"Михайлова",
							"Вълкова",
							"",
							"052/383-653",
							ASSISTANT_PROFESSOR,
							departments.get(2),
							"309НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_8", "1234", INSTRUCTOR),
							"Радостина",
							"Атанасова",
							"Христова-Минчева",
							"rahristova@tu-varna.bg",
							"052/383-653",
							ASSISTANT_PROFESSOR,
							departments.get(2),
							"309НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_EOOS_9", "1234", INSTRUCTOR),
							"Тодорка",
							"Денчева",
							"Станчева",
							"t.stankova@tu-varna.bg",
							"052/383-653",
							ASSISTANT_PROFESSOR,
							departments.get(2),
							"310НУК",
							false
					)
			);

			List<Instructor> instructorsEE = List.of(
					new Instructor(
							new AppUser("INSTR_EE_1", "1234", INSTRUCTOR),
							"Йончо",
							"Любенов",
							"Каменов",
							"j.kamenov@tu-varna.bg",
							"+359 52 383 218",
							ASSOCIATE_PROFESSOR,
							departments.get(3),
							"806Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_2", "1234", INSTRUCTOR),
							"Юлиян",
							"Емилов",
							"Рангелов",
							"y.rangelov@tu-varna.bg",
							"+359-52-383-218",
							ASSOCIATE_PROFESSOR,
							departments.get(3),
							"806Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_3", "1234", INSTRUCTOR),
							"Николай",
							"Деянов",
							"Николаев",
							"n.nikolaev@tu-varna.bg",
							"+359-52-383-218",
							ASSOCIATE_PROFESSOR,
							departments.get(3),
							"804Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_4", "1234", INSTRUCTOR),
							"Милена",
							"Димитрова",
							"Иванова",
							"m.dicheva@tu-varna.bg",
							"+359-52-383-203",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(3),
							"824Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_5", "1234", INSTRUCTOR),
							"Росица",
							"Филчева",
							"Димитрова",
							"r.dimitrova@tu-varna.bg",
							"+359-52-383-203",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(3),
							"824Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_6", "1234", INSTRUCTOR),
							"Антон",
							"Борисов",
							"Филипов",
							"filipov@tu-varna.bg",
							"+359 52 383 276",
							ASSISTANT_PROFESSOR,
							departments.get(3),
							"805Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_EE_7", "1234", INSTRUCTOR),
							"Пламен",
							"Антонов",
							"Станчев",
							"p.stanchev@tu-varna.bg",
							"+359 52 383 236",
							ASSISTANT_PROFESSOR,
							departments.get(3),
							"203Е",
							false
					)
			);

			for (Instructor instructor : instructorsAP)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsDTK)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsEOOS)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsEE)
				instructorService.addInstructor(instructor);
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

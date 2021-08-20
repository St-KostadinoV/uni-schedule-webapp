package com.example.unischedulewebapp;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserRole;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
					new AcademicFaculty("Машинно-технологичен факултет", "МФ"),
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
					new AcademicDepartment("Автоматизация на производството",  "АП", faculties.get(3)),														//0
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
					new AcademicDepartment("Корабоводене, управление на транспорта и опазване чистотата на водните пътища",  "КУТОЧВП", faculties.get(1)),
					new AcademicDepartment("Корабостроене, корабни машини и механизми",  "ККММ", faculties.get(1)),
					new AcademicDepartment("Колеж в структурата на ТУ-Варна",  "КТУ", faculties.get(5)),
					new AcademicDepartment("Математика и физика",  "МФ", faculties.get(2)),
					new AcademicDepartment("Материалознание и технология на материалите",  "МТМ", faculties.get(0)),											//15
					new AcademicDepartment("Механика и машинни елементи",  "ММЕ", faculties.get(0)),
					new AcademicDepartment("Общоуниверситетска работилница",  "ОУР", faculties.get(0)),
					new AcademicDepartment("Растениевъдство",  "Р", faculties.get(0)),
					new AcademicDepartment("Секция 'езиково обучение'",  "ЕО", faculties.get(6)),
					new AcademicDepartment("Секция 'математика'",  "М", faculties.get(2)),																		//20
					new AcademicDepartment("Секция 'физика'",  "Ф", faculties.get(2)),
					new AcademicDepartment("Секция 'физическо възпитание и спорт'",  "ФВС", faculties.get(6)),
					new AcademicDepartment("Софтуерни и интернет технологии",  "СИТ", faculties.get(3)),
					new AcademicDepartment("Социални и правни науки",  "СПН", faculties.get(2)),
					new AcademicDepartment("Теоретична и измервателна електротехника",  "ТИЕ", faculties.get(2)),												//25
					new AcademicDepartment("Технология на машиностроенето и металорежещи машини",  "ТМММ", faculties.get(0)),
					new AcademicDepartment("Топлотехника",  "ТТ", faculties.get(1)),
					new AcademicDepartment("Транспортна техника и технологии",  "ТТТ", faculties.get(0))														//28
			);
			for (AcademicDepartment department : departments)
				departmentService.addDepartment(department);

			List<AcademicProgram> bachelorPrograms = List.of(
					new AcademicProgram("Автоматика, информационни и управляващи компютърни системи", "АИУКС", departments.get(0), 0, BACHELORS, 4f),
					new AcademicProgram("Автомобилна техника", "АТ", departments.get(28), 0, BACHELORS, 4f),
					new AcademicProgram("Агрономство", "А", departments.get(18), 0, BACHELORS, 4f),
					new AcademicProgram("Биомедицинска електроника", "БМЕ", departments.get(4), 0, BACHELORS, 4f),
					new AcademicProgram("Възобновяеми енергийни източници", "ВЕИ", departments.get(6), 0, BACHELORS, 4f),
					new AcademicProgram("Електроенергетика", "ЕЕ", departments.get(3), 0, BACHELORS, 4f),
					new AcademicProgram("Електроника", "Е", departments.get(4), 0, BACHELORS, 4f),
					new AcademicProgram("Електрообзавеждане на кораба", "ЕОК", departments.get(5), 0, BACHELORS, 4f),
					new AcademicProgram("Електроснабдяване и електрообзавеждане", "ЕСЕО", departments.get(5), 0, BACHELORS, 4f),
					new AcademicProgram("Електротехника и електротехнологии", "ЕТЕТ", departments.get(6), 0, BACHELORS, 4f),
					new AcademicProgram("Защита на населението при бедствия и аварии", "ЗНБА", departments.get(2), 0, BACHELORS, 4f),
					new AcademicProgram("Индустриален дизайн", "ИД", departments.get(7), 0, BACHELORS, 4f),
					new AcademicProgram("Индустриален мениджмънт", "ИМ", departments.get(8), 0, BACHELORS, 4f),
					new AcademicProgram("Инженерна екология", "ИЕ", departments.get(2), 0, BACHELORS, 4f),
					new AcademicProgram("Информационни и комуникационн технологии", "ИКТ", departments.get(10), 0, BACHELORS, 4f),
					new AcademicProgram("Компютъризирани технологии в машиностроенето", "КТМ", departments.get(26), 0, BACHELORS, 4f),
					new AcademicProgram("Компютърни системи и технологии", "КСТ", departments.get(9), 0, BACHELORS, 4f),
					new AcademicProgram("Корабни машини и механизми", "КММ", departments.get(12), 0, BACHELORS, 4f),
					new AcademicProgram("Корабоводене", "КВ", departments.get(11), 0, BACHELORS, 4f),
					new AcademicProgram("Корабостроене и морска техника", "КМТ", departments.get(12), 0, BACHELORS, 4f),
					new AcademicProgram("Логистика на водния транспорт", "ЛВТ", departments.get(11), 0, BACHELORS, 4f),
					new AcademicProgram("Машиностроителна техника и технологии", "МТТ", departments.get(15), 0, BACHELORS, 4f),
					new AcademicProgram("Производствен инженеринг", "ПИ", departments.get(26), 0, BACHELORS, 4f),
					new AcademicProgram("Роботика и мехатроника", "РМ", departments.get(0), 0, BACHELORS, 4f),
					new AcademicProgram("Софтуерни и интернет технологии", "СИТ", departments.get(23), 0, BACHELORS, 4f),
					new AcademicProgram("Социален мениджмънт", "СМ", departments.get(24), 0, BACHELORS, 4f),
					new AcademicProgram("Технологично предприемачество и иновации", "ТПИ", departments.get(8), 0, BACHELORS, 4f),
					new AcademicProgram("Топлотехника и инвестиционно проектиране", "ТИП", departments.get(27), 0, BACHELORS, 4f),
					new AcademicProgram("Транспортна техника и технологии", "ТТТ", departments.get(28), 0, BACHELORS, 4f)
			);
			for (AcademicProgram program : bachelorPrograms)
				programService.addProgram(program);

			List<Instructor> instructorsAP = List.of(
					new Instructor(
							new AppUser("INSTR_AP_1", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_2", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_3", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_4", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_5", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_6", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_7", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_8", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_9", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_10", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_11", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_12", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_13", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_14", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_AP_15", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_1", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_2", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_3", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_4", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_5", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_6", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_7", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_DTK_8", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_1", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_2", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_3", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_4", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_5", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_6", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_7", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_8", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EOOS_9", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_1", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_2", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_3", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_4", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_5", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_6", "1234", AppUserRole.INSTRUCTOR),
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
							new AppUser("INSTR_EE_7", "1234", AppUserRole.INSTRUCTOR),
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

			List<Instructor> instructorsETM = List.of(
					new Instructor(
							new AppUser("INSTR_ETM_1", "1234", AppUserRole.INSTRUCTOR),
							"Емилиян",
							"Боянов",
							"Беков",
							"emilian.bekov@tu-varna.bg",
							"+359 52 383 437",
							ASSOCIATE_PROFESSOR,
							departments.get(4),
							"305Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_2", "1234", AppUserRole.INSTRUCTOR),
							"Венцислав",
							"Цеков",
							"Вълчев",
							"venci.valchev@tu-varna.bg",
							"+359 52 383 266",
							PROFESSOR,
							departments.get(4),
							"609Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_3", "1234", AppUserRole.INSTRUCTOR),
							"Ангел",
							"Станимиров",
							"Маринов",
							"a.marinov@tu-varna.bg",
							"+359 52 383 266",
							ASSOCIATE_PROFESSOR,
							departments.get(4),
							"609Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_4", "1234", AppUserRole.INSTRUCTOR),
							"Димитър",
							"Михайлов",
							"Ковачев",
							"dmk@tu-varna.bg",
							"+359 52 383 437",
							ASSOCIATE_PROFESSOR,
							departments.get(4),
							"501Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_5", "1234", AppUserRole.INSTRUCTOR),
							"Екатерина",
							"Николова",
							"Димитрова",
							"ekaterinad@tu-varna.bg",
							"+359 52 383 624",
							ASSOCIATE_PROFESSOR,
							departments.get(4),
							"501Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_6", "1234", AppUserRole.INSTRUCTOR),
							"Тончо",
							"Христов",
							"Папанчев",
							"t.papanchev@tu-varna.bg",
							"+359 52 383 619",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(4),
							"305аЕ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_7", "1234", AppUserRole.INSTRUCTOR),
							"Фирган",
							"Нихатов",
							"Ферадов",
							"firgan.feradov@tu-varna.bg",
							"+359 52 383 693",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(4),
							"208РСС",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_8", "1234", AppUserRole.INSTRUCTOR),
							"Георги",
							"Евтимов",
							"Тодоринов",
							"gtodorinov@tu-varna.bg",
							"+359 52 383 237",
							ASSISTANT_PROFESSOR,
							departments.get(4),
							"114Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_9", "1234", AppUserRole.INSTRUCTOR),
							"Антим",
							"Христов",
							"Йорданов",
							"a.yordanov@gmail.com",
							"+359 52 383 659",
							ASSISTANT_PROFESSOR,
							departments.get(4),
							"212РСС",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETM_10", "1234", AppUserRole.INSTRUCTOR),
							"Юлия",
							"Георгиева",
							"Гарипова",
							"juliq.garipova@tu-varna.bg",
							"+359 52 383 619",
							ASSISTANT_PROFESSOR,
							departments.get(4),
							"305аЕ",
							false
					)
			);

			List<Instructor> instructorsESEO = List.of(
					new Instructor(
							new AppUser("INSTR_ESEO_1", "1234", AppUserRole.INSTRUCTOR),
							"Валентин",
							"Николов",
							"Гюров",
							"valentin.giurov@tu-varna.bg",
							"+359 52 383515",
							ASSOCIATE_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_2", "1234", AppUserRole.INSTRUCTOR),
							"Владимир",
							"Чиков",
							"Чиков",
							"chikov@tu-varna.bg",
							"+359 52 383570",
							ASSOCIATE_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_3", "1234", AppUserRole.INSTRUCTOR),
							"Пламен",
							"Великов",
							"Парушев",
							"plamenparushev@tu-varna.bg",
							"+359 52 383573",
							ASSOCIATE_PROFESSOR,
							departments.get(5),
							"108aЕФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_4", "1234", AppUserRole.INSTRUCTOR),
							"Никола",
							"Иванов",
							"Македонски",
							"n.makedonski@tu-varna.bg",
							"+359 52 383515",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_5", "1234", AppUserRole.INSTRUCTOR),
							"Гинка",
							"Христова",
							"Иванова",
							"ginkahivanova@tu-varna.bg",
							"+359 52 383402",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_6", "1234", AppUserRole.INSTRUCTOR),
							"Николай",
							"Иванов",
							"Бежанов",
							"n.bezhanov@tu-varna.bg",
							"+359 52 383345",
							ASSISTANT_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_7", "1234", AppUserRole.INSTRUCTOR),
							"Христиан",
							"Ивайлов",
							"Панчев",
							"hr_panchev@abv.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(5),
							"104ЕФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_8", "1234", AppUserRole.INSTRUCTOR),
							"Георги",
							"Добромиров",
							"Милев",
							"g.milev@tu-varna.bg",
							"+359 52 383345",
							ASSISTANT_PROFESSOR,
							departments.get(5),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ESEO_9", "1234", AppUserRole.INSTRUCTOR),
							"Румен",
							"Михайлов",
							"Киров",
							"roumen.kirov@tu-varna.bg",
							"+359 52 383623",
							ASSOCIATE_PROFESSOR,
							departments.get(5),
							"",
							true
					)
			);

			List<Instructor> instructorsETET = List.of(
					new Instructor(
							new AppUser("INSTR_ETET_1", "1234", AppUserRole.INSTRUCTOR),
							"Майк",
							"Юрген",
							"Щреблау",
							"streblau@tu-varna.bg",
							"+359 52-383-540",
							ASSOCIATE_PROFESSOR,
							departments.get(6),
							"702Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_2", "1234", AppUserRole.INSTRUCTOR),
							"Бохос",
							"Рупен",
							"Апрахамян",
							"bohos@tu-varna.bg",
							"+359 52 383 578",
							PROFESSOR,
							departments.get(6),
							"206Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_3", "1234", AppUserRole.INSTRUCTOR),
							"Мария",
							"Иванова",
							"Маринова",
							"m_i_marinova@tu-varna.bg",
							"+359 52 383 523",
							ASSOCIATE_PROFESSOR,
							departments.get(6),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_4", "1234", AppUserRole.INSTRUCTOR),
							"Татяна",
							"Маринова",
							"Димова",
							"t.dimova@tu-varna.bg",
							"+359 52 383 319",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(6),
							"834Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_5", "1234", AppUserRole.INSTRUCTOR),
							"Янита",
							"Стоянова",
							"Славова",
							"slavova_yanita@tu-varna.bg",
							"+359 52 383 328",
							ASSISTANT_PROFESSOR,
							departments.get(6),
							"835Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_6", "1234", AppUserRole.INSTRUCTOR),
							"Надежда",
							"Димитрова",
							"Цветкова",
							"nadea@tu-varna.bg",
							"+359 52 383 323",
							ASSISTANT_PROFESSOR,
							departments.get(6),
							"836Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_ETET_7", "1234", AppUserRole.INSTRUCTOR),
							"Георги",
							"Димитров",
							"Желев",
							"zhelev@tu-varna.bg",
							"+359 899 108 279",
							ASSISTANT_PROFESSOR,
							departments.get(6),
							"833Е",
							false
					)
			);

			List<Instructor> instructorsID = List.of(
					new Instructor(
							new AppUser("INSTR_ID_1", "1234", AppUserRole.INSTRUCTOR),
							"Пламен",
							"Василев",
							"Братанов",
							"bratanov@tu-varna.bg",
							"052 383-301",
							PROFESSOR,
							departments.get(7),
							"502МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_2", "1234", AppUserRole.INSTRUCTOR),
							"Момчил",
							"Тодоров",
							"Тачев",
							"m_tachev@tu-varna.bg",
							"052 383-309",
							ASSOCIATE_PROFESSOR,
							departments.get(7),
							"515аМТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_3", "1234", AppUserRole.INSTRUCTOR),
							"Цена",
							"Радкова",
							"Мурзова",
							"murzova@tu-varna.bg",
							"052 383-309",
							ASSOCIATE_PROFESSOR,
							departments.get(7),
							"14НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_4", "1234", AppUserRole.INSTRUCTOR),
							"Тихомир",
							"Атанасов",
							"Доврамаджиев",
							"tihomir.dovramadjiev@tu-varna.bg",
							"052 383-300",
							ASSOCIATE_PROFESSOR,
							departments.get(7),
							"516МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_5", "1234", AppUserRole.INSTRUCTOR),
							"Гинка",
							"Великова",
							"Жечева",
							"ginkaa@tu-varna.bg",
							"052 383-300",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(7),
							"516МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_6", "1234", AppUserRole.INSTRUCTOR),
							"Кремена",
							"Цанкова",
							"Цанкова",
							"kremenacankova@tu-varna.bg",
							"052 383-300",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(7),
							"516МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_7", "1234", AppUserRole.INSTRUCTOR),
							"Илия",
							"Наумов",
							"Илиев",
							"i.iliev@tu-varna.bg",
							"052 383-300",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(7),
							"516МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_8", "1234", AppUserRole.INSTRUCTOR),
							"Галина",
							"Димитрова",
							"Станева",
							"g.staneva@tu-varna.bg",
							"052 383-307",
							ASSISTANT_PROFESSOR,
							departments.get(7),
							"505МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_9", "1234", AppUserRole.INSTRUCTOR),
							"Дарина",
							"Недкова",
							"Добрева",
							"darina.dobreva@tu-varna.bg",
							"052 383-307",
							ASSISTANT_PROFESSOR,
							departments.get(7),
							"505МТФ",
							false
					),
					new Instructor(
							new AppUser("INSTR_ID_10", "1234", AppUserRole.INSTRUCTOR),
							"Венцислав",
							"Георгиев",
							"Марков",
							"ventsi_markov@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(7),
							"",
							false
					)
			);

			List<Instructor> instructorsIM = List.of(
					new Instructor(
							new AppUser("INSTR_IM_1", "1234", AppUserRole.INSTRUCTOR),
							"Сийка",
							"Димитрова",
							"Демирова",
							"s_demirova@tu-varna.bg",
							"052/383 612",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"501НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_2", "1234", AppUserRole.INSTRUCTOR),
							"Светлана",
							"Райчева",
							"Димитракиева",
							"s_dimitrakieva@tu-varna.bg",
							"052/383 613",
							PROFESSOR,
							departments.get(8),
							"503НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_3", "1234", AppUserRole.INSTRUCTOR),
							"Светлана",
							"Костова",
							"Лесидренска",
							"sv_lesidrenska@tu-varna.bg",
							"052/383 263",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"510НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_4", "1234", AppUserRole.INSTRUCTOR),
							"Таня",
							"Пенчева",
							"Панайотова",
							"t_panayotova@tu-varna.bg",
							"052/383 682",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"508НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_5", "1234", AppUserRole.INSTRUCTOR),
							"Красимира",
							"Атанасова",
							"Димитрова",
							"krasimira.dimitrova@tu-varna.bg",
							"052/383 682",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"508НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_6", "1234", AppUserRole.INSTRUCTOR),
							"Беанета",
							"Василева",
							"Янева",
							"b.vasileva@tu-varna.bg",
							"052/383 254",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"517бНУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_7", "1234", AppUserRole.INSTRUCTOR),
							"Мариана",
							"Радкова",
							"Мурзова",
							"m.murzova@tu-varna.bg",
							"052/383 336",
							ASSISTANT_PROFESSOR,
							departments.get(8),
							"509НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_8", "1234", AppUserRole.INSTRUCTOR),
							"Сибел",
							"Илханова",
							"Ахмедова",
							"sibel_ahmet@tu-varna.bg",
							"052/383 602",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(8),
							"504НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_9", "1234", AppUserRole.INSTRUCTOR),
							"Весела",
							"Борисова",
							"Дичева",
							"vdicheva@tu-varna.bg",
							"052/383 336",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(8),
							"509НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_10", "1234", AppUserRole.INSTRUCTOR),
							"Марина",
							"Петрова",
							"Маринова",
							"",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(8),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_IM_11", "1234", AppUserRole.INSTRUCTOR),
							"Дарина",
							"Маринова",
							"Павлова",
							"pavlovad@tu-varna.bg",
							"052/383 602",
							ASSOCIATE_PROFESSOR,
							departments.get(8),
							"504НУК",
							false
					)
			);


			List<Instructor> instructorsKNT = List.of(
					new Instructor(
							new AppUser("INSTR_KNT_1", "1234", AppUserRole.INSTRUCTOR),
							"Христо",
							"Георгиев",
							"Вълчанов",
							"hristo@tu-varna.bg",
							"052 383 439",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"207-4Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_2", "1234", AppUserRole.INSTRUCTOR),
							"Тодор",
							"Димитров",
							"Ганчев",
							"tganchev@tu-varna.bg",
							"052 383 621",
							PROFESSOR,
							departments.get(9),
							"104ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_3", "1234", AppUserRole.INSTRUCTOR),
							"Слава",
							"Миланова",
							"Йорданова",
							"slava_y@tu-varna.bg",
							"052 383 638",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"406ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_4", "1234", AppUserRole.INSTRUCTOR),
							"Владимир",
							"Николов",
							"Николов",
							"nikolov_vn@tu-varna.bg",
							"052 383 621",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"513аЕ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_5", "1234", AppUserRole.INSTRUCTOR),
							"Милена",
							"Николова",
							"Карова",
							"mkarova@tu-varna.bg",
							"052 383 409",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"205ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_6", "1234", AppUserRole.INSTRUCTOR),
							"Юлка",
							"Петкова",
							"Петкова",
							"yulka.petkova@tu-varna.bg",
							"052 383 403",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"207-3Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_7", "1234", AppUserRole.INSTRUCTOR),
							"Венета",
							"Панайотова",
							"Алексиева",
							"valeksieva@tu-varna.bg",
							"052 383 439",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"207-4Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_8", "1234", AppUserRole.INSTRUCTOR),
							"Ивайло",
							"Пламенов",
							"Пенев",
							"ivailo.penev@tu-varna.bg",
							"052 383 409",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"205ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_9", "1234", AppUserRole.INSTRUCTOR),
							"Жейно",
							"Иванов",
							"Жейнов",
							"zh_viv@tu-varna.bg",
							"052 383 260",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"407ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_10", "1234", AppUserRole.INSTRUCTOR),
							"Венцислав",
							"Георгиев",
							"Николов",
							"v.nikolov@tu-varna.bg",
							"052 383 433",
							ASSOCIATE_PROFESSOR,
							departments.get(9),
							"101ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_11", "1234", AppUserRole.INSTRUCTOR),
							"Лъчезар",
							"Илиев",
							"Георгиев",
							"lig@tu-varna.bg",
							"052 383 628",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(9),
							"405ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_12", "1234", AppUserRole.INSTRUCTOR),
							"Милен",
							"Георгиев",
							"Ангелов",
							"angelovmg@tu-varna.bg",
							"052 383 628",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(9),
							"405ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_13", "1234", AppUserRole.INSTRUCTOR),
							"Гергана",
							"Василева",
							"Спасова",
							"g.spasova@tu-varna.bg",
							"052 383 679",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(9),
							"210ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_14", "1234", AppUserRole.INSTRUCTOR),
							"Айдън",
							"Мехмед",
							"Хъкъ",
							"aydin.mehmed@tu-varna.bg",
							"052 383 424",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(9),
							"102ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_15", "1234", AppUserRole.INSTRUCTOR),
							"Илиян",
							"Живков",
							"Бойчев",
							"i.boychev@tu-varna.bg",
							"052 383 679",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(9),
							"210ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_16", "1234", AppUserRole.INSTRUCTOR),
							"Гинка",
							"Калева",
							"Маринова",
							"gmarinova@tu-varna.bg",
							"052 383 407",
							ASSISTANT_PROFESSOR,
							departments.get(9),
							"410ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_17", "1234", AppUserRole.INSTRUCTOR),
							"Галина",
							"Тодорова",
							"Найденова",
							"galina.naydenova@tu-varna.bg",
							"052 383 621",
							ASSISTANT_PROFESSOR,
							departments.get(9),
							"104ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_18", "1234", AppUserRole.INSTRUCTOR),
							"Пламена",
							"Живкова",
							"Едрева",
							"plamena.edreva@tu-varna.bg",
							"052 383 424",
							ASSISTANT_PROFESSOR,
							departments.get(9),
							"102ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_19", "1234", AppUserRole.INSTRUCTOR),
							"Петко",
							"Генчев",
							"Генчев",
							"p.genchev@tu-varna.bg",
							"052 383 675",
							ASSISTANT_PROFESSOR,
							departments.get(9),
							"406ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KNT_20", "1234", AppUserRole.INSTRUCTOR),
							"Пролет",
							"Стойчева",
							"Денева",
							"prolet_deneva@tu-varna.bg",
							"053 383 433",
							ASSISTANT_PROFESSOR,
							departments.get(9),
							"101ТВ",
							false
					)
			);

			List<Instructor> instructorsKTT = List.of(
					new Instructor(
							new AppUser("INSTR_KTT_1", "1234", AppUserRole.INSTRUCTOR),
							"Николай",
							"Тодоров",
							"Костов",
							"n_kostov@tu-varna.bg",
							"052 383 596",
							ASSOCIATE_PROFESSOR,
							departments.get(10),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_2", "1234", AppUserRole.INSTRUCTOR),
							"Розалина",
							"Стефанова",
							"Димова",
							"rdim@tu-varna.bg",
							"052 383 350",
							PROFESSOR,
							departments.get(10),
							"403Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_3", "1234", AppUserRole.INSTRUCTOR),
							"Борислав",
							"Георгиев",
							"Найденов",
							"borna@tu-varna.bg",
							"052 383 214",
							ASSOCIATE_PROFESSOR,
							departments.get(10),
							"405Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_4", "1234", AppUserRole.INSTRUCTOR),
							"Валентина",
							"Илиева",
							"Маркова",
							"via@tu-varna.bg",
							"052 383 677",
							ASSOCIATE_PROFESSOR,
							departments.get(10),
							"409Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_5", "1234", AppUserRole.INSTRUCTOR),
							"Тодорка",
							"Николова",
							"Георгиева",
							"t_georgieva@tu-varna.bg",
							"052 383 304",
							ASSOCIATE_PROFESSOR,
							departments.get(10),
							"405Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_6", "1234", AppUserRole.INSTRUCTOR),
							"Любомир",
							"Петров",
							"Камбуров",
							"lkamburov@tu-varna.bg",
							"052 383 674",
							ASSOCIATE_PROFESSOR,
							departments.get(10),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_7", "1234", AppUserRole.INSTRUCTOR),
							"Пламен",
							"Йоргов",
							"Стоянов",
							"PL_ST0IANOV@tu-varna.bg",
							"052 383 514",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(10),
							"412Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_8", "1234", AppUserRole.INSTRUCTOR),
							"Стела",
							"Савова",
							"Костадинова",
							"stela.kostadinova@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(10),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_9", "1234", AppUserRole.INSTRUCTOR),
							"Борис",
							"Николаев",
							"Николов",
							"boris.nikolov@tu-varna.bg",
							"052 383 630",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(10),
							"605Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_10", "1234", AppUserRole.INSTRUCTOR),
							"Георги",
							"Цанев",
							"Червенков",
							"g.t.chervenkov@tu-varna.bg",
							"052 383 252",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(10),
							"506Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_11", "1234", AppUserRole.INSTRUCTOR),
							"Мартин",
							"Владимиров",
							"Иванов",
							"",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(10),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_12", "1234", AppUserRole.INSTRUCTOR),
							"Георги",
							"Петров",
							"Бебров",
							"",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(10),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KTT_13", "1234", AppUserRole.INSTRUCTOR),
							"Калин",
							"Боянов",
							"Калинков",
							"",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(10),
							"",
							false
					)

			);

			List<Instructor> instructorsKUTOCHVP = List.of(
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_1", "1234", AppUserRole.INSTRUCTOR),
							"Чавдар",
							"Илиев",
							"Александрова",
							"",
							"",
							PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_2", "1234", AppUserRole.INSTRUCTOR),
							"Анастас",
							"Стефанов",
							"Крушев",
							"anastas.krushev@tu-varna.bg",
							"",
							ASSOCIATE_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_3", "1234", AppUserRole.INSTRUCTOR),
							"Анета",
							"Димитрова",
							"Върбанова",
							"",
							"",
							ASSOCIATE_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_4", "1234", AppUserRole.INSTRUCTOR),
							"Божидар",
							"Николов",
							"Дяков",
							"bndyakov@tu-varna.bg",
							"",
							ASSOCIATE_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_5", "1234", AppUserRole.INSTRUCTOR),
							"Иван",
							"Иванчев",
							"Грозев",
							"i.grozev@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(11),
							"409НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_6", "1234", AppUserRole.INSTRUCTOR),
							"Милен",
							"Живков",
							"Тодоров",
							"",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_7", "1234", AppUserRole.INSTRUCTOR),
							"Николай",
							"Маринов",
							"Беджев",
							"BEDZHEV@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_8", "1234", AppUserRole.INSTRUCTOR),
							"Божидар",
							"Петров",
							"Събев",
							"sabev@tu-varna.bg",
							"385 709",
							ASSISTANT_PROFESSOR,
							departments.get(11),
							"308НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_9", "1234", AppUserRole.INSTRUCTOR),
							"Веселин",
							"Атанасов",
							"Митев",
							"",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_10", "1234", AppUserRole.INSTRUCTOR),
							"Боряна",
							"Генчева",
							"Генова",
							"bgenova@tu-varna.bg",
							"385 708",
							SENIOR_INSTRUCTOR,
							departments.get(11),
							"408НУК",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_11", "1234", AppUserRole.INSTRUCTOR),
							"Анна",
							"Георгиева",
							"Миткова",
							"anna_mitkova@tu-varna.bg",
							"",
							INSTRUCTOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_12", "1234", AppUserRole.INSTRUCTOR),
							"Детелина",
							"Костадинова",
							"Кокърчина",
							"RITEOFPASSAGE@tu-varna.bg",
							"",
							INSTRUCTOR,
							departments.get(11),
							"",
							false
					),
					new Instructor(
							new AppUser("INSTR_KUTOCHVP_13", "1234", AppUserRole.INSTRUCTOR),
							"Есин",
							"Махмуд",
							"Халид",
							"ehalid@tu-varna.bg",
							"",
							INSTRUCTOR,
							departments.get(11),
							"",
							false
					)
			);

			List<Instructor> instructorsKKMM = List.of(
					new Instructor(
							new AppUser("INSTR_KKMM_1", "1234", AppUserRole.INSTRUCTOR),
							"Галина",
							"Илиева",
							"Илиева",
							"galili@tu-varna.bg",
							"",
							ASSOCIATE_PROFESSOR,
							departments.get(12),
							"406М",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_2", "1234", AppUserRole.INSTRUCTOR),
							"Ирина",
							"Добрева",
							"Костова",
							"irina.kostova@tu-varna.bg",
							"383 476",
							ASSOCIATE_PROFESSOR,
							departments.get(12),
							"318аМ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_3", "1234", AppUserRole.INSTRUCTOR),
							"Петър",
							"Георгиев",
							"Георгиев",
							"petar.ge@tu-varna.bg",
							"052 383-384",
							ASSOCIATE_PROFESSOR,
							departments.get(12),
							"201УПБ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_4", "1234", AppUserRole.INSTRUCTOR),
							"Христо",
							"Атанасов",
							"Пировски",
							"christo.pirovsky@tu-varna.bg",
							"383 571",
							ASSOCIATE_PROFESSOR,
							departments.get(12),
							"402М",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_5", "1234", AppUserRole.INSTRUCTOR),
							"Йорданка",
							"Георгиева",
							"Съчкова",
							"",
							"052 383-370",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(12),
							"418М",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_6", "1234", AppUserRole.INSTRUCTOR),
							"Йордан",
							"Стефанов",
							"Денев",
							"",
							"052 383-384",
							ASSISTANT_PROFESSOR,
							departments.get(12),
							"418аМ",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_7", "1234", AppUserRole.INSTRUCTOR),
							"Стефан",
							"Николов",
							"Кюлевчелиев",
							"kyulef@yahoo.com",
							"052 383-370",
							ASSOCIATE_PROFESSOR,
							departments.get(12),
							"235УПБ",
							true
					),
					new Instructor(
							new AppUser("INSTR_KKMM_8", "1234", AppUserRole.INSTRUCTOR),
							"Владимир",
							"Атанасов",
							"Йорданов",
							"vyordanov@tu-varna.bg",
							"052 383 344",
							ASSISTANT_PROFESSOR,
							departments.get(12),
							"405М",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_9", "1234", AppUserRole.INSTRUCTOR),
							"Севдалин",
							"Здравков",
							"Вълчев",
							"sevdalin.valchev@tu-varna.bg",
							"383 571",
							ASSISTANT_PROFESSOR,
							departments.get(12),
							"415М",
							false
					),
					new Instructor(
							new AppUser("INSTR_KKMM_10", "1234", AppUserRole.INSTRUCTOR),
							"Христо",
							"Антонов",
							"Маринов",
							"h_marinov79@yahoo.com",
							"383 344",
							ASSISTANT_PROFESSOR,
							departments.get(12),
							"405М",
							false
					)
			);

			List<Instructor> instructorsSIT = List.of(
					new Instructor(
							new AppUser("INSTR_SIT_1", "1234", AppUserRole.INSTRUCTOR),
							"Виолета",
							"Тодорова",
							"Божикова",
							"vbojikova@tu-varna.bg",
							"052 383 616",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"310ТВ, 206ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_2", "1234", AppUserRole.INSTRUCTOR),
							"Недялко",
							"Николаев",
							"Николов",
							"ned.nikolov@tu-varna.bg",
							"052 383 628",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"303аТВ, 404аТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_3", "1234", AppUserRole.INSTRUCTOR),
							"Марияна",
							"Цветанова",
							"Стоева",
							"mariana_stoeva@tu-varna.bg",
							"052 383 616",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"310ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_4", "1234", AppUserRole.INSTRUCTOR),
							"Гео",
							"Василев",
							"Кунев",
							"geo_kunev@tu-varna.bg",
							"052 383 407",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"408ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_5", "1234", AppUserRole.INSTRUCTOR),
							"Златка",
							"Тенева",
							"Матева",
							"mateva@tu-varna.bg",
							"052 383 228",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"807Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_6", "1234", AppUserRole.INSTRUCTOR),
							"Христо",
							"Божидаров",
							"Ненов",
							"h.nenov@tu-varna.bg",
							"052 383 403",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"305ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_7", "1234", AppUserRole.INSTRUCTOR),
							"Росен",
							"Стефанов",
							"Радков",
							"rossen.radkov@tu-varna.bg",
							"052 383 403",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(23),
							"207Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_8", "1234", AppUserRole.INSTRUCTOR),
							"Ивелин",
							"Методиев",
							"Иванов",
							"i.ivanov@tu-varna.bg",
							"052 383 228",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(23),
							"807Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_9", "1234", AppUserRole.INSTRUCTOR),
							"Нели",
							"Ананиева",
							"Арабаджиева-Калчева",
							"n_kalcheva@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(23),
							"107ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_10", "1234", AppUserRole.INSTRUCTOR),
							"Павлина",
							"Стоянова",
							"Владимирова",
							"pavlast@tu-varna.bg",
							"052 383 628",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"405ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_11", "1234", AppUserRole.INSTRUCTOR),
							"Мая",
							"Петрова",
							"Тодорова",
							"maya.todorova@tu-varna.bg",
							"052 383 657",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"410ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_12", "1234", AppUserRole.INSTRUCTOR),
							"Антоанета",
							"Иванова",
							"Иванова",
							"antoaneta_ii@tu-varna.bg",
							"052 383 407",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"408аТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_13", "1234", AppUserRole.INSTRUCTOR),
							"Стефка",
							"Иванова",
							"Попова",
							"stefka.popova@tu-varna.bg",
							"052 383 657",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"410ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_14", "1234", AppUserRole.INSTRUCTOR),
							"Димитричка",
							"Желева",
							"Николаева",
							"d.nikolaeva@tu-varna.bg",
							"0899/168983",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(23),
							"106ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_15", "1234", AppUserRole.INSTRUCTOR),
							"Диян",
							"Желев",
							"Динев",
							"diyandinev@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"106ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_16", "1234", AppUserRole.INSTRUCTOR),
							"Павлина",
							"Стоянова",
							"Линова",
							"polly.linova@tu-varna.bg",
							"0899 904 947",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"608аЕ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_17", "1234", AppUserRole.INSTRUCTOR),
							"Велислав",
							"Василев",
							"Колесниченко",
							"vkolesnichenko@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"305ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_18", "1234", AppUserRole.INSTRUCTOR),
							"Галя",
							"Димитрова",
							"Господинова",
							"",
							"052 383 576",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"107ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_19", "1234", AppUserRole.INSTRUCTOR),
							"Мирослав",
							"Весков",
							"Марков",
							"",
							"052 383 228",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"807Е",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_20", "1234", AppUserRole.INSTRUCTOR),
							"Доника",
							"Георгиева",
							"Стоянова",
							"donika.stoyanova@tu-varna.bg",
							"",
							ASSISTANT_PROFESSOR,
							departments.get(23),
							"608аЕ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_21", "1234", AppUserRole.INSTRUCTOR),
							"Елена",
							"Викторовна",
							"Рачева",
							"elracheva@yahoo.com",
							"052 383 628",
							ASSOCIATE_PROFESSOR,
							departments.get(23),
							"404ТВ",
							false
					),
					new Instructor(
							new AppUser("INSTR_SIT_22", "1234", AppUserRole.INSTRUCTOR),
							"Ганка",
							"Петкова",
							"Калчева",
							"gpp_kov@tu-varna.bg",
							"",
							SENIOR_ASSISTANT_PROFESSOR,
							departments.get(23),
							"305ТВ",
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
			for (Instructor instructor : instructorsETM)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsESEO)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsETET)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsID)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsIM)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsKNT)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsKTT)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsKUTOCHVP)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsKKMM)
				instructorService.addInstructor(instructor);
			for (Instructor instructor : instructorsSIT)
				instructorService.addInstructor(instructor);

			List<AcademicDiscipline> disciplines = List.of(
					new AcademicDiscipline(
							"WEB приложения",
							"WEB прил.",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"WEB програмиране",
							"WEB пр.",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Бази от данни",
							"БД",
							departments.get(23),
							instructorsSIT.get(3)
					),
					new AcademicDiscipline(
							"Базова математика",
							"БМ",
							departments.get(23),
							instructorsSIT.get(4)
					),
					new AcademicDiscipline(
							"Базова математика за инженери",
							"БМИ",
							departments.get(23),
							instructorsSIT.get(4)
					),
					new AcademicDiscipline(
							"Графични системи",
							"ГС",
							departments.get(23),
							instructorsSIT.get(2)
					),
					new AcademicDiscipline(
							"Интернет сървъри и услуги",
							"ИСУ",
							departments.get(23),
							instructorsSIT.get(6)
					),
					new AcademicDiscipline(
							"Интернет технологии",
							"ИТ",
							departments.get(23),
							instructorsSIT.get(5)
					),
					new AcademicDiscipline(
							"Информатика",
							"Инф.",
							departments.get(23),
							instructorsSIT.get(1)
					),
					new AcademicDiscipline(
							"Информатика и компютърна техника",
							"ИКТ",
							departments.get(23),
							instructorsSIT.get(1)
					),
					new AcademicDiscipline(
							"Информационни системи и технологии",
							"ИСТ",
							departments.get(23),
							instructorsSIT.get(20)
					),
					new AcademicDiscipline(
							"Компютърна графика",
							"КФ",
							departments.get(23),
							instructorsSIT.get(2)
					),
					new AcademicDiscipline(
							"Компютърна графика и визуализация",
							"КФВ",
							departments.get(23),
							instructorsSIT.get(2)
					),
					new AcademicDiscipline(
							"Компютърни архитектури",
							"КА",
							departments.get(23),
							instructorsSIT.get(5)
					),
					new AcademicDiscipline(
							"Компютърни мрежи в Интернет",
							"КМИ",
							departments.get(23),
							instructorsSIT.get(6)
					),
					new AcademicDiscipline(
							"Компютърни технологии",
							"КТ",
							departments.get(23),
							instructorsSIT.get(20)
					),
					new AcademicDiscipline(
							"Криптография и защита на данни",
							"КЗД",
							departments.get(23),
							instructorsSIT.get(4)
					),
					new AcademicDiscipline(
							"Математика за компютърни науки",
							"МКН",
							departments.get(23),
							instructorsSIT.get(4)
					),
					new AcademicDiscipline(
							"Мрежово администриране",
							"МА",
							departments.get(23),
							instructorsSIT.get(6)
					),
					new AcademicDiscipline(
							"Мултимедийни системи и технологии",
							"МСТ",
							departments.get(23),
							instructorsSIT.get(5)
					),
					new AcademicDiscipline(
							"Офис технологии",
							"ОТ",
							departments.get(23),
							instructorsSIT.get(20)
					),
					new AcademicDiscipline(
							"Програмиране за мобилни Интернет устройства",
							"ПМИУ",
							departments.get(23),
							instructorsSIT.get(5)
					),
					new AcademicDiscipline(
							"Програмни системи",
							"ПС",
							departments.get(23),
							instructorsSIT.get(2)
					),
					new AcademicDiscipline(
							"Програмни спецификации",
							"ПС",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Програмни технологии в Интернет",
							"ПТИ",
							departments.get(23),
							instructorsSIT.get(5)
					),
					new AcademicDiscipline(
							"Проектиране на WEB приложения",
							"ПWП",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Проектиране на WEB програмни приложения",
							"ПWПП",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Проектиране на информационни системи",
							"ПИС",
							departments.get(23),
							instructorsSIT.get(1)
					),
					new AcademicDiscipline(
							"Синтез и анализ на алгоритми",
							"САА",
							departments.get(23),
							instructorsSIT.get(20)
					),
					new AcademicDiscipline(
							"Системен анализ",
							"СА",
							departments.get(23),
							instructorsSIT.get(1)
					),
					new AcademicDiscipline(
							"Системи с бази от данни",
							"СБД",
							departments.get(23),
							instructorsSIT.get(3)
					),
					new AcademicDiscipline(
							"Софтуерни изисквания и спецификации",
							"СИС",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Софтуерни технологии",
							"СТ",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Технология на софтуерното производство",
							"ТСП",
							departments.get(23),
							instructorsSIT.get(0)
					),
					new AcademicDiscipline(
							"Управление на данните",
							"УД",
							departments.get(23),
							instructorsSIT.get(3)
					),
					new AcademicDiscipline(
							"WEB дизайн",
							"WEB д.",
							departments.get(9),
							instructorsKNT.get(2)
					),
					new AcademicDiscipline(
							"Администриране на локални и Интернет мрежи",
							"АЛИМ",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Базово програмиране",
							"БП",
							departments.get(9),
							instructorsKNT.get(4)
					),
					new AcademicDiscipline(
							"Взаимодействие човек-компютър",
							"ВЧК",
							departments.get(9),
							instructorsKNT.get(4)
					),
					new AcademicDiscipline(
							"Графичен дизайн",
							"ГС",
							departments.get(9),
							instructorsKNT.get(2)
					),
					new AcademicDiscipline(
							"Дискретни структури",
							"ДС",
							departments.get(9),
							instructorsKNT.get(7)
					),
					new AcademicDiscipline(
							"Езикови процесори",
							"ЕП",
							departments.get(9),
							instructorsKNT.get(7)
					),
					new AcademicDiscipline(
							"Електронна търговия",
							"ЕТ",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Извличане на информация в Интернет",
							"ИИИ",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Интернет сървъри и технологии",
							"ИСТ",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Компилатори и интерпретатори",
							"КИ",
							departments.get(9),
							instructorsKNT.get(7)
					),
					new AcademicDiscipline(
							"Компютърна и мрежова сигурност",
							"КМС",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Компютърни архитектури",
							"КА",
							departments.get(9),
							instructorsKNT.get(10)
					),
					new AcademicDiscipline(
							"Компютърни мрежи",
							"КМ",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Логика и автомати",
							"ЛА",
							departments.get(9),
							instructorsKNT.get(5)
					),
					new AcademicDiscipline(
							"Микропроцесорна техника",
							"МПТ",
							departments.get(9),
							instructorsKNT.get(10)
					),
					new AcademicDiscipline(
							"Микропроцесорни системи",
							"МПС",
							departments.get(9),
							instructorsKNT.get(1)
					),
					new AcademicDiscipline(
							"Обектно-ориентирано програмиране - 1 част",
							"ООП-1ч.",
							departments.get(9),
							instructorsKNT.get(3)
					),
					new AcademicDiscipline(
							"Обектно-ориентирано програмиране - 2 част",
							"ООП-2ч.",
							departments.get(9),
							instructorsKNT.get(3)
					),
					new AcademicDiscipline(
							"Операционни системи",
							"ОС",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Организация на компютъра",
							"ОК",
							departments.get(9),
							instructorsKNT.get(5)
					),
					new AcademicDiscipline(
							"Основи на компютърните комуникации",
							"ОКК",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Принципи на операционните системи",
							"ПОС",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Проектиране на компютърни мрежи",
							"ПКМ",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Проектиране на потребителски интерфейси",
							"ППИ",
							departments.get(9),
							instructorsKNT.get(4)
					),
					new AcademicDiscipline(
							"Системно програмиране",
							"СП",
							departments.get(9),
							instructorsKNT.get(9)
					),
					new AcademicDiscipline(
							"Специализирани компютърни системи",
							"СКС",
							departments.get(9),
							instructorsKNT.get(5)
					),
					new AcademicDiscipline(
							"Управление на софтуерни проекти",
							"УСП",
							departments.get(9),
							instructorsKNT.get(4)
					),
					new AcademicDiscipline(
							"Цифрови системи",
							"ЦС",
							departments.get(9),
							instructorsKNT.get(2)
					),
					new AcademicDiscipline(
							"Мрежови инфраструктури",
							"МИ",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Интегрирани компютърни системи и мрежи",
							"ИКСМ",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Виртуализационни технологии",
							"ВТ",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Разпределени и мрежови операционни системи",
							"РМОС",
							departments.get(9),
							instructorsKNT.get(0)
					),
					new AcademicDiscipline(
							"Безжични комуникации",
							"БК",
							departments.get(9),
							instructorsKNT.get(6)
					),
					new AcademicDiscipline(
							"Машинно обучение",
							"МО",
							departments.get(9),
							instructorsKNT.get(7)
					),
					new AcademicDiscipline(
							"Скриптови езици",
							"СЕ",
							departments.get(9),
							instructorsKNT.get(7)
					),
					new AcademicDiscipline(
							"Основи на изкуствения интелект",
							"ОИИ",
							departments.get(9),
							instructorsKNT.get(4)
					)
			);
		};
	}
}

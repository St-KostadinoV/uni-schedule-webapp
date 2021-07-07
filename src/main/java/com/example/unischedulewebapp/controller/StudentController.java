package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.PasswordsMatchException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.service.AcademicTimetableService;
import com.example.unischedulewebapp.service.StudentService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "api/v1/student"
)
public class StudentController {

    private final StudentService studentService;
    private final AcademicTimetableService timetableService;
    private final AppUserService userService;

    @Autowired
    public StudentController(StudentService studentService,
                             AcademicTimetableService timetableService,
                             AppUserService userService) {
        this.studentService = studentService;
        this.timetableService = timetableService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students = studentService
                .findAll(Sort.by(Sort.Direction.ASC, "lastName"));

        MappingJacksonValue wrapper = new MappingJacksonValue(students);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("StudentFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "facultyNumber",
                                                                        "firstName",
                                                                        "middleName",
                                                                        "lastName",
                                                                        "academicYear",
                                                                        "academicProgram"))
                .addFilter("ProgramFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{studentId}"
    )
    public ResponseEntity<Object> getStudentDetails(@PathVariable("studentId") Long id) {
        try {
            Student student = studentService.findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(student);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("StudentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "facultyNumber",
                                                                            "firstName",
                                                                            "middleName",
                                                                            "lastName",
                                                                            "academicYear",
                                                                            "academicProgram",
                                                                            "admissionStream",
                                                                            "studentGroup",
                                                                            "activeStatus"))
                    .addFilter("ProgramFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("name"));

            wrapper.setFilters(filters);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(wrapper);

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "daily-schedule"
    )
    public ResponseEntity<Object> getDailySchedule() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            List<AcademicTimetable> schedule = timetableService
                    .findStudentDailySchedule(studentService.findByUserDetails(currentUser));

            MappingJacksonValue wrapper = new MappingJacksonValue(schedule);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("startTime",
                                                                            "endTime",
                                                                            "classType",
                                                                            "programDiscipline",
                                                                            "designatedRoom",
                                                                            "assignedTeacher"))
                    .addFilter("ProgramDisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("discipline"))
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("TeacherFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                            "title",
                                                                            "firstName",
                                                                            "lastName"));

            wrapper.setFilters(filters);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(wrapper);

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "weekly-schedule"
    )
    public ResponseEntity<Object> getWeeklySchedule() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            List<AcademicTimetable> schedule = timetableService
                    .findStudentWeeklySchedule(studentService.findByUserDetails(currentUser));

            MappingJacksonValue wrapper = new MappingJacksonValue(schedule);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("dayOfWeek",
                                                                            "startTime",
                                                                            "endTime",
                                                                            "classType",
                                                                            "programDiscipline",
                                                                            "designatedRoom",
                                                                            "assignedTeacher"))
                    .addFilter("ProgramDisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("discipline"))
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("TeacherFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                            "title",
                                                                            "firstName",
                                                                            "lastName"));

            wrapper.setFilters(filters);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(wrapper);

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping(
            path = "pass-change"
    )
    public ResponseEntity<Object> updateStudentPassword(@RequestParam("newPassword") String newPassword,
                                                        @RequestParam("oldPassword") String oldPassword) {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            userService.updatePassword(currentUser, newPassword, oldPassword);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (PasswordsMatchException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}

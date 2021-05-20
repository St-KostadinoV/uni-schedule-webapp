package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.PasswordsMatchException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.service.AcademicTimetableService;
import com.example.unischedulewebapp.service.TeacherService;
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
        path = "api/v1/teacher"
)
public class TeacherController {

    private final TeacherService teacherService;
    private final AcademicTimetableService timetableService;
    private final AppUserService userService;

    @Autowired
    public TeacherController(TeacherService teacherService,
                             AcademicTimetableService timetableService,
                             AppUserService userService) {
        this.teacherService = teacherService;
        this.timetableService = timetableService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers() {
        List<Teacher> teachers = teacherService
                .findAll(Sort.by(Sort.Direction.ASC, "lastName"));

        MappingJacksonValue wrapper = new MappingJacksonValue(teachers);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("TeacherFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "honoraryStatus",
                                                                        "title",
                                                                        "firstName",
                                                                        "middleName",
                                                                        "lastName",
                                                                        "department"))
                .addFilter("DepartmentFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{teacherId}"
    )
    public ResponseEntity<Object> getTeacherDetails(@PathVariable("teacherId") Long id) {
        try {
            Teacher teacher = teacherService.findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(teacher);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TeacherFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "honoraryStatus",
                                                                            "title",
                                                                            "firstName",
                                                                            "middleName",
                                                                            "lastName",
                                                                            "department",
                                                                            "office",
                                                                            "email",
                                                                            "phone"))
                    .addFilter("DepartmentFilter",
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
                    .findTeacherDailySchedule(teacherService.findByUserDetails(currentUser));

            MappingJacksonValue wrapper = new MappingJacksonValue(schedule);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("startTime",
                                                                            "endTime",
                                                                            "classType",
                                                                            "programDiscipline",
                                                                            "designatedRoom",
                                                                            "studentGroup"))
                    .addFilter("ProgramDisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("program",
                                                                            "discipline",
                                                                            "academicYear"))
                    .addFilter("ProgramFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("DisciplineFilter",
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
            path = "weekly-schedule"
    )
    public ResponseEntity<Object> getWeeklySchedule() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            List<AcademicTimetable> schedule = timetableService
                    .findByAssignedTeacher(teacherService.findByUserDetails(currentUser));

            MappingJacksonValue wrapper = new MappingJacksonValue(schedule);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("dayOfWeek",
                                                                            "startTime",
                                                                            "endTime",
                                                                            "classType",
                                                                            "programDiscipline",
                                                                            "designatedRoom",
                                                                            "studentGroup"))
                    .addFilter("ProgramDisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("program",
                                                                            "discipline",
                                                                            "academicYear"))
                    .addFilter("ProgramFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("DisciplineFilter",
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

    @PostMapping(
            path = "email-change"
    )
    public ResponseEntity<Object> updateTeacherEmail(@RequestParam("email") String email) {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            Teacher teacher = teacherService.findByUserDetails(currentUser);
            teacherService.updateTeacherEmail(teacher, email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

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
    public ResponseEntity<Object> updateTeacherPassword(@RequestParam("password") String password,
                                                        @RequestParam("oldPassword") String oldPassword) {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            userService.updatePassword(currentUser, password, oldPassword);
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

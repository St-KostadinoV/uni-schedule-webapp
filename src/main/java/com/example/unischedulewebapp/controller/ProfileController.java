package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserRole;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.PasswordsMatchException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.service.AcademicTimetableService;
import com.example.unischedulewebapp.service.StudentService;
import com.example.unischedulewebapp.service.InstructorService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000"
)
@RequestMapping(
        path = "profile"
)
public class ProfileController {

    private final AppUserService userService;
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final AcademicTimetableService timetableService;

    @Autowired
    public ProfileController(AppUserService userService,
                             StudentService studentService,
                             InstructorService instructorService,
                             AcademicTimetableService timetableService) {
        this.userService = userService;
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.timetableService = timetableService;
    }

    @GetMapping
    public ResponseEntity<Object> getAuthenticatedUserProfile() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            MappingJacksonValue wrapper;
            FilterProvider filters;

            if (currentUser.getAppUserRole().equals(AppUserRole.INSTRUCTOR)) {
                wrapper = new MappingJacksonValue(instructorService.findByUserDetails(currentUser));
                filters = new SimpleFilterProvider()
                        .addFilter("InstructorFilter",
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
            }
            else {
                wrapper = new MappingJacksonValue(studentService.findByUserDetails(currentUser));
                filters = new SimpleFilterProvider()
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
            }

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
    public ResponseEntity<Object> updateAuthenticatedUserEmail(@RequestParam("email") String email) {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            Instructor instructor = instructorService
                    .findByUserDetails(currentUser);

            instructorService.updateInstructorEmail(instructor, email);

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
    public ResponseEntity<Object> updateAuthenticatedUserPassword(@RequestParam("newPassword") String newPassword,
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
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "timetable/daily"
    )
    public ResponseEntity<Object> getAuthenticatedUserDailyTimetable() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            List<AcademicTimetable> timetable;

            FilterProvider filters;

            if (currentUser.getAppUserRole().equals(AppUserRole.INSTRUCTOR)) {
                Instructor instructor = instructorService
                        .findByUserDetails(currentUser);

                timetable = timetableService
                        .findInstructorDailySchedule(instructor);

                filters = new SimpleFilterProvider()
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
            }
            else {
                Student student = studentService
                        .findByUserDetails(currentUser);

                timetable = timetableService
                        .findStudentDailySchedule(student);

                filters = new SimpleFilterProvider()
                        .addFilter("TimetableFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("startTime",
                                                                                "endTime",
                                                                                "classType",
                                                                                "programDiscipline",
                                                                                "designatedRoom",
                                                                                "assignedInstructor"))
                        .addFilter("ProgramDisciplineFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("discipline"))
                        .addFilter("DisciplineFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("name"))
                        .addFilter("InstructorFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                                "title",
                                                                                "firstName",
                                                                                "lastName"));
            }

            MappingJacksonValue wrapper = new MappingJacksonValue(timetable);
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
            path = "timetable/weekly"
    )
    public ResponseEntity<Object> getAuthenticatedUserWeeklyTimetable() {
        AppUser currentUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            List<AcademicTimetable> timetable;

            FilterProvider filters;

            if (currentUser.getAppUserRole().equals(AppUserRole.INSTRUCTOR)) {
                Instructor instructor = instructorService
                        .findByUserDetails(currentUser);

                timetable = timetableService
                        .findByAssignedInstructor(instructor);

                filters = new SimpleFilterProvider()
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
            }
            else {
                Student student = studentService
                        .findByUserDetails(currentUser);

                timetable = timetableService
                        .findStudentWeeklySchedule(student);

                filters = new SimpleFilterProvider()
                        .addFilter("TimetableFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("startTime",
                                                                                "endTime",
                                                                                "classType",
                                                                                "programDiscipline",
                                                                                "designatedRoom",
                                                                                "assignedInstructor"))
                        .addFilter("ProgramDisciplineFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("discipline"))
                        .addFilter("DisciplineFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("name"))
                        .addFilter("InstructorFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                                "title",
                                                                                "firstName",
                                                                                "lastName"));
            }

            MappingJacksonValue wrapper = new MappingJacksonValue(timetable);
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
}

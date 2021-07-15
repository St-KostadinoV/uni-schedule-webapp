package com.example.unischedulewebapp.controller.v2;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserRole;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.service.StudentService;
import com.example.unischedulewebapp.service.TeacherService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "profile"
)
public class ProfileController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public ProfileController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }
    // TODO - finish controller
//    @GetMapping
//    public ResponseEntity<Object> getAuthenticatedUserProfile() {
//        AppUser currentUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//        try {
//            MappingJacksonValue wrapper;
//            FilterProvider filters;
//
//            if (currentUser.getAppUserRole().equals(AppUserRole.TEACHER)) {
//                wrapper = new MappingJacksonValue(teacherService.findByUserDetails(currentUser));
//                filters = new SimpleFilterProvider()
//                        .addFilter("TeacherFilter",
//                                    SimpleBeanPropertyFilter.filterOutAllExcept("id",
//                                                                                "honoraryStatus",
//                                                                                "title",
//                                                                                "firstName",
//                                                                                "middleName",
//                                                                                "lastName",
//                                                                                "department",
//                                                                                "office",
//                                                                                "email",
//                                                                                "phone"))
//                        .addFilter("DepartmentFilter",
//                                    SimpleBeanPropertyFilter.filterOutAllExcept("name"));
//            }
//            else {
//                wrapper = new MappingJacksonValue(studentService.findByUserDetails(currentUser));
//                filters = new SimpleFilterProvider()
//                        .addFilter("StudentFilter",
//                                    SimpleBeanPropertyFilter.filterOutAllExcept("id",
//                                                                                "facultyNumber",
//                                                                                "firstName",
//                                                                                "middleName",
//                                                                                "lastName",
//                                                                                "academicYear",
//                                                                                "academicProgram",
//                                                                                "admissionStream",
//                                                                                "studentGroup",
//                                                                                "activeStatus"))
//                        .addFilter("ProgramFilter",
//                                    SimpleBeanPropertyFilter.filterOutAllExcept("name"));
//            }
//
//            wrapper.setFilters(filters);
//
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(wrapper);
//
//        } catch (ResourceNotFoundException e) {
//            // TODO - log stack trace
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        }
//    }
//
//    @PostMapping(
//            path = "email-change"
//    )
//    public ResponseEntity<Object> updateAuthenticatedUserEmail(@RequestParam("email") String email) {
//        AppUser currentUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//    }
//
//    @PostMapping(
//            path = "pass-change"
//    )
//    public ResponseEntity<Object> updateAuthenticatedUserPassword(@RequestParam("newPassword") String newPassword,
//                                                                  @RequestParam("oldPassword") String oldPassword) {
//        AppUser currentUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//    }
//
//    @GetMapping(
//            path = "timetable/daily"
//    )
//    public ResponseEntity<Object> getAuthenticatedUserDailyTimetable() {
//        AppUser currentUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//    }
//
//    @GetMapping(
//            path = "timetable/weekly"
//    )
//    public ResponseEntity<Object> getAuthenticatedUserWeeklyTimetable() {
//        AppUser currentUser = (AppUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//    }
}

package com.example.unischedulewebapp.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000"
)
@RequestMapping(
        path = "students"
)
public class StudentController {

    private final StudentService studentService;
    private final AcademicTimetableService timetableService;

    @Autowired
    public StudentController(StudentService studentService,
                             AcademicTimetableService timetableService) {
        this.studentService = studentService;
        this.timetableService = timetableService;
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
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") Long id) {
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
                                                                            "studentGroup",
                                                                            "email",
                                                                            "phone"))
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
            path = "{studentId}/timetable"
    )
    public ResponseEntity<Object> getStudentTimetable(@PathVariable("studentId") Long id) {
        try {
            Student student = studentService.findById(id);

            List<AcademicTimetable> studentTimetable = timetableService
                    .findStudentWeeklySchedule(student);

            MappingJacksonValue wrapper = new MappingJacksonValue(studentTimetable);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "dayOfWeek",
                                                                            "startTime",
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
                                                                            "degree",
                                                                            "qualification",
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
}

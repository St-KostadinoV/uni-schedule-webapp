package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.Student;
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

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
}

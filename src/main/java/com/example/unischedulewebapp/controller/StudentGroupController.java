package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.service.AcademicProgramService;
import com.example.unischedulewebapp.service.StudentService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "api/v1/group-data"
)
public class StudentGroupController {

    private final StudentService studentService;
    private final AcademicProgramService programService;

    @Autowired
    public StudentGroupController(StudentService studentService,
                                  AcademicProgramService programService) {
        this.studentService = studentService;
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<Object> getGroupData(@RequestParam("group") Integer group,
                                               @RequestParam("program") Long programId,
                                               @RequestParam("year") Integer year) {
        try {
            AcademicProgram program = programService.findById(programId);
            List<Student> students = studentService.findByAcademicProgramAndStudentGroupAndAcademicYear(program, group, year);

            MappingJacksonValue wrapper = new MappingJacksonValue(students);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("StudentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "facultyNumber",
                                                                            "firstName",
                                                                            "middleName",
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

package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.service.AcademicProgramService;
import com.example.unischedulewebapp.service.ProgramDisciplineService;
import com.example.unischedulewebapp.service.StudentService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "programs"
)
public class ProgramController {

    private final AcademicProgramService programService;
    private final ProgramDisciplineService programDisciplineService;
    private final StudentService studentService;

    @Autowired
    public ProgramController(AcademicProgramService programService,
                             ProgramDisciplineService programDisciplineService,
                             StudentService studentService) {
        this.programService = programService;
        this.programDisciplineService = programDisciplineService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPrograms() {
        List<AcademicProgram> programs = programService
                .findAll();

        MappingJacksonValue wrapper = new MappingJacksonValue(programs);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("ProgramFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "name",
                                                                        "abbreviation",
                                                                        "academicStream"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{programId}"
    )
    public ResponseEntity<Object> getProgram(@PathVariable("programId") Long id) {
        try {
            AcademicProgram program = programService
                    .findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(program);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("ProgramFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "academicStream"));

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
            path = "{programId}/disciplines"
    )
    public ResponseEntity<Object> getProgramDisciplines(@PathVariable("programId") Long id,
                                                        @RequestParam(name = "year", required = false) Integer year) {
        try {
            AcademicProgram program = programService
                    .findById(id);

            List<AcademicDiscipline> programDisciplines = programDisciplineService
                    .findProgramDisciplines(program, year);

            MappingJacksonValue wrapper = new MappingJacksonValue(programDisciplines);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "department",
                                                                            "leadingInstructor"))
                    .addFilter("DepartmentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("InstructorFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("firstName",
                                                                            "lastName",
                                                                            "title",
                                                                            "honoraryStatus"));

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
            path = "{programId}/students"
    )
    public ResponseEntity<Object> getProgramStudents(@PathVariable("programId") Long id,
                                                     @RequestParam(name = "year", required = false) Integer year,
                                                     @RequestParam(name = "group", required = false) Integer group) {
        try {
            AcademicProgram program = programService
                    .findById(id);

            List<Student> programStudents = studentService
                    .findProgramStudents(program, year, group);

            MappingJacksonValue wrapper = new MappingJacksonValue(programStudents);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("StudentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "facultyNumber",
                                                                            "firstName",
                                                                            "middleName",
                                                                            "lastName",
                                                                            "academicYear",
                                                                            "studentGroup",
                                                                            "activeStatus"));

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

package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
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
        path = "disciplines"
)
public class DisciplineController {

    private final AcademicDisciplineService disciplineService;

    @Autowired
    public DisciplineController(AcademicDisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDisciplines() {
        List<AcademicDiscipline> disciplines = disciplineService
                .findAll(Sort.by(Sort.Direction.ASC, "name"));

        MappingJacksonValue wrapper = new MappingJacksonValue(disciplines);

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
    }

    @GetMapping(
            path = "{disciplineId}"
    )
    public ResponseEntity<Object> getDiscipline(@PathVariable("disciplineId") Long id) {
        try {
            AcademicDiscipline discipline = disciplineService
                    .findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(discipline);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "department",
                                                                            "leadingInstructor"))
                    .addFilter("DepartmentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("name"))
                    .addFilter("InstructorFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                            "title",
                                                                            "degree",
                                                                            "qualification",
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

    @GetMapping(
            path = "{disciplineId}/instructors"
    )
    public ResponseEntity<Object> getDisciplineInstructors(@PathVariable("disciplineId") Long id) {
        try {
            AcademicDiscipline discipline = disciplineService
                    .findById(id);

            List<Instructor> disciplineInstructors = discipline
                    .getAllInstructors();

            MappingJacksonValue wrapper = new MappingJacksonValue(disciplineInstructors);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("InstructorFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "firstName",
                                                                            "middleName",
                                                                            "lastName",
                                                                            "title",
                                                                            "degree",
                                                                            "qualification",
                                                                            "honoraryStatus",
                                                                            "office"));

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

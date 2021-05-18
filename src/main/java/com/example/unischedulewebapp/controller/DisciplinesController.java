package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        path = "api/v1/discipline"
)
public class DisciplinesController {

    private final AcademicDisciplineService disciplineService;

    @Autowired
    public DisciplinesController(AcademicDisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity getAllDisciplines() {
        List<AcademicDiscipline> disciplines = disciplineService
                .findAll(Sort.by(Sort.Direction.ASC, "name"));

        MappingJacksonValue wrapper = new MappingJacksonValue(disciplines);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("DisciplineFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("name","abbreviation","disciplineUrl"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{disciplineId}"
    )
    public ResponseEntity getDisciplineDetails(@PathVariable("disciplineId") Long id) {
        try {
            AcademicDiscipline discipline = disciplineService
                    .findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(discipline);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("name","abbreviation","department","leadingTeacher","disciplineUrl"))
                    .addFilter("DepartmentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("name"))
                    .addFilter("TeacherFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("title","firstName","middleName","lastName"));

            wrapper.setFilters(filters);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(wrapper);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}

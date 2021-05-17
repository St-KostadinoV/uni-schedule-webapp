package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MappingJacksonValue getAllDisciplines() {
        List<AcademicDiscipline> disciplines = disciplineService
                .findAll(Sort.by(Sort.Direction.ASC, "name"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","abbreviation","disciplineUrl");
        FilterProvider filters = new SimpleFilterProvider().addFilter("DisciplineFilter", filter);
        MappingJacksonValue  mapping = new MappingJacksonValue(disciplines);

        mapping.setFilters(filters);

        return mapping;
    }
}

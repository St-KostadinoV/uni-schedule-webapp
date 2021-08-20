package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicFaculty;
import com.example.unischedulewebapp.service.AcademicDepartmentService;
import com.example.unischedulewebapp.service.AcademicFacultyService;
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
@CrossOrigin(
        origins = "http://localhost:3000"
)
@RequestMapping(
        path = "faculties"
)
public class FacultyController {

    private final AcademicFacultyService facultyService;
    private final AcademicDepartmentService departmentService;

    @Autowired
    public FacultyController(AcademicFacultyService facultyService,
                             AcademicDepartmentService departmentService) {
        this.facultyService = facultyService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllFaculties() {
        List<AcademicFaculty> faculties = facultyService
                .findAll();

        MappingJacksonValue wrapper = new MappingJacksonValue(faculties);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("FacultyFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "name",
                                                                        "abbreviation"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{facultyId}"
    )
    public ResponseEntity<Object> getFaculty(@PathVariable("facultyId") Long id) {
        try {
            MappingJacksonValue wrapper = new MappingJacksonValue(facultyService.findById(id));

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("FacultyFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation"));

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
            path = "{facultyId}/departments"
    )
    public ResponseEntity<Object> getFacultyDepartments(@PathVariable("facultyId") Long id) {
        try {
            AcademicFaculty faculty = facultyService
                    .findById(id);

            List<AcademicDepartment> facultyDepartments = departmentService
                    .findByFaculty(faculty);

            MappingJacksonValue wrapper = new MappingJacksonValue(facultyDepartments);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DepartmentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation"));

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

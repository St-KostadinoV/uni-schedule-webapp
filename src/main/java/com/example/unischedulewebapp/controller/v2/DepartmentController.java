package com.example.unischedulewebapp.controller.v2;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.service.AcademicDepartmentService;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
import com.example.unischedulewebapp.service.AcademicProgramService;
import com.example.unischedulewebapp.service.TeacherService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "departments"
)
public class DepartmentController {

    private final AcademicDepartmentService departmentService;
    private final AcademicDisciplineService disciplineService;
    private final AcademicProgramService programService;
    private final TeacherService teacherService;

    @Autowired
    public DepartmentController(AcademicDepartmentService departmentService, AcademicDisciplineService disciplineService, AcademicProgramService programService, TeacherService teacherService) {
        this.departmentService = departmentService;
        this.disciplineService = disciplineService;
        this.programService = programService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDepartments(){
        List<AcademicDepartment> departments = departmentService
                .findAll();

        MappingJacksonValue wrapper = new MappingJacksonValue(departments);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("DepartmentFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "name",
                                                                        "abbreviation",
                                                                        "faculty"))
                .addFilter("FacultyFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{departmentId}"
    )
    public ResponseEntity<Object> getDepartment(@PathVariable("departmentId") Long id) {
        try {
            MappingJacksonValue wrapper = new MappingJacksonValue(departmentService.findById(id));

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DepartmentFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "faculty"))
                    .addFilter("FacultyFilter",
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
            path = "{departmentId}/disciplines"
    )
    public ResponseEntity<Object> getDepartmentDisciplines(@PathVariable("departmentId") Long id) {
        try {
            AcademicDepartment department = departmentService
                    .findById(id);

            List<AcademicDiscipline> departmentDisciplines = disciplineService
                    .findByDepartment(department);

            MappingJacksonValue wrapper = new MappingJacksonValue(departmentDisciplines);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "leadingTeacher"))
                    .addFilter("TeacherFilter",
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
            path = "{departmentId}/programs"
    )
    public ResponseEntity<Object> getDepartmentPrograms(@PathVariable("departmentId") Long id) {
        try {
            AcademicDepartment department = departmentService
                    .findById(id);

            List<AcademicProgram> departmentPrograms = programService
                    .findByDepartment(department);

            MappingJacksonValue wrapper = new MappingJacksonValue(departmentPrograms);

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
            path = "{departmentId}/teachers"
    )
    public ResponseEntity<Object> getDepartmentTeachers(@PathVariable("departmentId") Long id) {
        try {
            AcademicDepartment department = departmentService
                    .findById(id);

            List<Teacher> departmentTeachers = teacherService
                    .findByDepartment(department);

            MappingJacksonValue wrapper = new MappingJacksonValue(departmentTeachers);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TeacherFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "firstName",
                                                                        "middleName",
                                                                        "lastName",
                                                                        "title",
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

package com.example.unischedulewebapp.controller.v2;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
import com.example.unischedulewebapp.service.AcademicTimetableService;
import com.example.unischedulewebapp.service.TeacherService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        path = "teachers"
)
public class TeacherController {

    private final TeacherService teacherService;
    private  final AcademicDisciplineService disciplineService;
    private final AcademicTimetableService timetableService;

    @Autowired
    public TeacherController(TeacherService teacherService, AcademicDisciplineService disciplineService, AcademicTimetableService timetableService) {
        this.teacherService = teacherService;
        this.disciplineService = disciplineService;
        this.timetableService = timetableService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers() {
        List<Teacher> teachers = teacherService
                .findAll(Sort.by(Sort.Direction.ASC, "lastName"));

        MappingJacksonValue wrapper = new MappingJacksonValue(teachers);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("TeacherFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                "honoraryStatus",
                                "title",
                                "firstName",
                                "middleName",
                                "lastName",
                                "department"))
                .addFilter("DepartmentFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"));

        wrapper.setFilters(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wrapper);
    }

    @GetMapping(
            path = "{teacherId}"
    )
    public ResponseEntity<Object> getTeacherDetails(@PathVariable("teacherId") Long id) {
        try {
            Teacher teacher = teacherService.findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(teacher);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TeacherFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                    "honoraryStatus",
                                    "title",
                                    "firstName",
                                    "middleName",
                                    "lastName",
                                    "department",
                                    "office",
                                    "email",
                                    "phone"))
                    .addFilter("DepartmentFilter",
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
            path = "{teacherId}/disciplines"
    )
    public ResponseEntity<Object> getTeacherDisciplines(@PathVariable("teacherId") Long id) {
        try {
            Teacher teacher = teacherService.findById(id);

            // TODO - rework to show all disciplines the teacher teaches, not only the ones they lead
            List<AcademicDiscipline> teacherDisciplines = disciplineService
                    .findByLeadingTeacher(teacher);

            MappingJacksonValue wrapper = new MappingJacksonValue(teacherDisciplines);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                        "name",
                                                                        "abbreviation",
                                                                        "leadingTeacher"))
                    .addFilter("TeacherFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("honoraryStatus",
                                                                        "title",
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
            path = "{teacherId}/timetable"
    )
    public ResponseEntity<Object> getTeacherTimetable(@PathVariable("teacherId") Long id) {
        try {
            Teacher teacher = teacherService.findById(id);

            List<AcademicTimetable> teacherTimetable = timetableService
                    .findByAssignedTeacher(teacher);

            MappingJacksonValue wrapper = new MappingJacksonValue(teacherTimetable);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("TimetableFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("dayOfWeek",
                                    "startTime",
                                    "endTime",
                                    "classType",
                                    "programDiscipline",
                                    "designatedRoom",
                                    "studentGroup"))
                    .addFilter("ProgramDisciplineFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("program",
                                    "discipline",
                                    "academicYear"))
                    .addFilter("ProgramFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("abbreviation"))
                    .addFilter("DisciplineFilter",
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

package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.service.AcademicDisciplineService;
import com.example.unischedulewebapp.service.AcademicTimetableService;
import com.example.unischedulewebapp.service.InstructorService;
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
        path = "instructors"
)
public class InstructorController {

    private final InstructorService instructorService;
    private  final AcademicDisciplineService disciplineService;
    private final AcademicTimetableService timetableService;

    @Autowired
    public InstructorController(InstructorService instructorService,
                                AcademicDisciplineService disciplineService,
                                AcademicTimetableService timetableService) {
        this.instructorService = instructorService;
        this.disciplineService = disciplineService;
        this.timetableService = timetableService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllInstructors() {
        List<Instructor> instructors = instructorService
                .findAll(Sort.by(Sort.Direction.ASC, "lastName"));

        MappingJacksonValue wrapper = new MappingJacksonValue(instructors);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("InstructorFilter",
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
            path = "{instructorId}"
    )
    public ResponseEntity<Object> getInstructorDetails(@PathVariable("instructorId") Long id) {
        try {
            Instructor instructor = instructorService.findById(id);

            MappingJacksonValue wrapper = new MappingJacksonValue(instructor);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("InstructorFilter",
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
            path = "{instructorId}/disciplines"
    )
    public ResponseEntity<Object> getInstructorDisciplines(@PathVariable("instructorId") Long id) {
        try {
            Instructor instructor = instructorService.findById(id);

            // TODO - rework to show all disciplines the teacher teaches, not only the ones they lead
            List<AcademicDiscipline> teacherDisciplines = disciplineService
                    .findByLeadingInstructor(instructor);

            MappingJacksonValue wrapper = new MappingJacksonValue(teacherDisciplines);

            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("DisciplineFilter",
                                SimpleBeanPropertyFilter.filterOutAllExcept("id",
                                                                            "name",
                                                                            "abbreviation",
                                                                            "leadingInstructor"))
                    .addFilter("InstructorFilter",
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
    public ResponseEntity<Object> getInstructorTimetable(@PathVariable("teacherId") Long id) {
        try {
            Instructor instructor = instructorService.findById(id);

            List<AcademicTimetable> teacherTimetable = timetableService
                    .findByAssignedInstructor(instructor);

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

package com.example.unischedulewebapp.controller.v2;

import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(
        path = "admin"
)
public class AdminController {

    private final AcademicDepartmentService departmentService;
    private final AcademicDisciplineService disciplineService;
    private final AcademicFacultyService facultyService;
    private final AcademicProgramService programService;
    private final AcademicTimetableService timetableService;
    private final ProgramDisciplineService programDisciplineService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public AdminController(AcademicDepartmentService departmentService,
                                AcademicDisciplineService disciplineService,
                                AcademicFacultyService facultyService,
                                AcademicProgramService programService,
                                AcademicTimetableService timetableService,
                                ProgramDisciplineService programDisciplineService,
                                TeacherService teacherService,
                                StudentService studentService) {
        this.departmentService = departmentService;
        this.disciplineService = disciplineService;
        this.facultyService = facultyService;
        this.programService = programService;
        this.timetableService = timetableService;
        this.programDisciplineService = programDisciplineService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping("department")
    public ResponseEntity<Object> addDepartment(@RequestBody AcademicDepartment department) throws URISyntaxException {
        try {
            AcademicDepartment newDepartment = departmentService.addDepartment(department);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/department/" + newDepartment.getId()))
                    .body(newDepartment);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("department/{departmentId}")
    public ResponseEntity<Object> updateDepartment(@PathVariable("departmentId") Long id,
                                                   @RequestBody AcademicDepartment department){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(departmentService.updateDepartment(id,department));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("department/{departmentId}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable("departmentId") Long id){
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("discipline")
    public ResponseEntity<Object> addDiscipline(@RequestBody AcademicDiscipline discipline) throws URISyntaxException {
        try {
            AcademicDiscipline newDiscipline = disciplineService.addDiscipline(discipline);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/discipline/" + newDiscipline.getId()))
                    .body(newDiscipline);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("discipline/{disciplineId}")
    public ResponseEntity<Object> updateDiscipline(@PathVariable("disciplineId") Long id,
                                                   @RequestBody AcademicDiscipline discipline){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(disciplineService.updateDiscipline(id,discipline));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("discipline/{disciplineId}")
    public ResponseEntity<Object> deleteDiscipline(@PathVariable("disciplineId") Long id){
        try {
            disciplineService.deleteDiscipline(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("faculty")
    public ResponseEntity<Object> addFaculty(@RequestBody AcademicFaculty faculty) throws URISyntaxException {
        try {
            AcademicFaculty newFaculty = facultyService.addFaculty(faculty);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/faculty/" + newFaculty.getId()))
                    .body(newFaculty);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PutMapping("faculty/{facultyId}")
    public ResponseEntity<Object> updateFaculty(@PathVariable("facultyId") Long id,
                                                @RequestBody AcademicFaculty faculty){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(facultyService.updateFaculty(id,faculty));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("faculty/{facultyId}")
    public ResponseEntity<Object> deleteFaculty(@PathVariable("facultyId") Long id){
        try {
            facultyService.deleteFaculty(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("program")
    public ResponseEntity<Object> addProgram(@RequestBody AcademicProgram program) throws URISyntaxException {
        try {
            AcademicProgram newProgram = programService.addProgram(program);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/program/" + newProgram.getId()))
                    .body(newProgram);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("program/{programId}")
    public ResponseEntity<Object> updateProgram(@PathVariable("programId") Long id,
                                                @RequestBody AcademicProgram program){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(programService.updateProgram(id,program));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("program/{programId}")
    public ResponseEntity<Object> deleteProgram(@PathVariable("programId") Long id){
        try {
            programService.deleteProgram(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("program-discipline")
    public ResponseEntity<Object> addProgramDiscipline(@RequestBody ProgramDiscipline programDiscipline) throws URISyntaxException {
        try {
            ProgramDiscipline newProgramDiscipline = programDisciplineService.addProgramDiscipline(programDiscipline);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/program-discipline/" + newProgramDiscipline.getId()))
                    .body(newProgramDiscipline);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("program-discipline/{programDisciplineId}")
    public ResponseEntity<Object> updateProgramDiscipline(@PathVariable("programDisciplineId") Long id,
                                                          @RequestBody ProgramDiscipline programDiscipline){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(programDisciplineService.updateProgramDiscipline(id,programDiscipline));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("program-discipline/{programDisciplineId}")
    public ResponseEntity<Object> deleteProgramDiscipline(@PathVariable("programDisciplineId") Long id){
        try {
            programService.deleteProgram(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("student")
    public ResponseEntity<Object> addStudent(@RequestBody Student student) throws URISyntaxException {
        try {
            Student newStudent = studentService.addStudent(student);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/student/" + newStudent.getId()))
                    .body(newStudent);

        } catch (ResourceAlreadyExistsException | UserAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("student/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long id,
                                                @RequestBody Student student){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentService.updateStudent(id,student));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("student/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long id){
        try {
            studentService.deleteStudent(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("teacher")
    public ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher) throws URISyntaxException {
        try {
            Teacher newTeacher = teacherService.addTeacher(teacher);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/teacher/" + newTeacher.getId()))
                    .body(newTeacher);

        } catch (ResourceAlreadyExistsException | UserAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("teacher/{teacherId}")
    public ResponseEntity<Object> updateTeacher(@PathVariable("teacherId") Long id,
                                                @RequestBody Teacher teacher){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(teacherService.updateTeacher(id,teacher));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("teacher/{teacherId}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("teacherId") Long id){
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("timetable")
    public ResponseEntity<Object> addTimetable(@RequestBody AcademicTimetable timetable) throws URISyntaxException {
        try {
            AcademicTimetable newTimetable = timetableService.addTimetable(timetable);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("api/v1/management/timetable/" + newTimetable.getId()))
                    .body(newTimetable);

        } catch (ResourceAlreadyExistsException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("timetable/{timetableId}")
    public ResponseEntity<Object> updateTimetable(@PathVariable("timetableId") Long id,
                                                  @RequestBody AcademicTimetable timetable){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(timetableService.updateTimetable(id,timetable));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("timetable/{timetableId}")
    public ResponseEntity<Object> deleteTimetable(@PathVariable("timetableId") Long id){
        try {
            timetableService.deleteTimetable(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

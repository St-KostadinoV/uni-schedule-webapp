package com.example.unischedulewebapp.controller;

import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.exception.TimetableCollisionException;
import com.example.unischedulewebapp.model.*;
import com.example.unischedulewebapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000"
)
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
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Autowired
    public AdminController(AcademicDepartmentService departmentService,
                           AcademicDisciplineService disciplineService,
                           AcademicFacultyService facultyService,
                           AcademicProgramService programService,
                           AcademicTimetableService timetableService,
                           ProgramDisciplineService programDisciplineService,
                           InstructorService instructorService,
                           StudentService studentService) {
        this.departmentService = departmentService;
        this.disciplineService = disciplineService;
        this.facultyService = facultyService;
        this.programService = programService;
        this.timetableService = timetableService;
        this.programDisciplineService = programDisciplineService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    //<editor-fold desc="Department methods">
    @GetMapping("department")
    public ResponseEntity<Object> getAllDepartments(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.findAll());
    }

    @GetMapping("department/{departmentId}")
    public ResponseEntity<Object> getDepartment(@PathVariable("departmentId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(departmentService.findById(id));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("department")
    public ResponseEntity<Object> addDepartment(@RequestBody AcademicDepartment department) throws URISyntaxException {
        try {
            AcademicDepartment newDepartment = departmentService.addDepartment(department);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("department/" + newDepartment.getId()))
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
    //</editor-fold>

    //<editor-fold desc="Discipline methods">
    @GetMapping("discipline")
    public ResponseEntity<Object> getAllDisciplines(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(disciplineService.findAll());
    }

    @GetMapping("discipline/{disciplineId}")
    public ResponseEntity<Object> getDiscipline(@PathVariable("disciplineId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(disciplineService.findById(id));

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
                    .location(new URI("discipline/" + newDiscipline.getId()))
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
    //</editor-fold>

    //<editor-fold desc="Faculty methods">
    @GetMapping("faculty")
    public ResponseEntity<Object> getAllFaculties(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(facultyService.findAll());
    }

    @GetMapping("faculty/{facultyId}")
    public ResponseEntity<Object> getFaculty(@PathVariable("facultyId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(facultyService.findById(id));

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
                    .location(new URI("faculty/" + newFaculty.getId()))
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
    //</editor-fold>

    //<editor-fold desc="Program methods">
    @GetMapping("program")
    public ResponseEntity<Object> getAllPrograms(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(programService.findAll());
    }

    @GetMapping("program/{programId}")
    public ResponseEntity<Object> getProgram(@PathVariable("programId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(programService.findById(id));

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
                    .location(new URI("program/" + newProgram.getId()))
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
    //</editor-fold>

    //<editor-fold desc="Program-Discipline methods">
    @GetMapping("program-discipline")
    public ResponseEntity<Object> getAllProgramDisciplines(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(programDisciplineService.findAll());
    }

    @GetMapping("program-discipline/{programDisciplineId}")
    public ResponseEntity<Object> getProgramDiscipline(@PathVariable("programDisciplineId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(programDisciplineService.findById(id));

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
                    .location(new URI("program-discipline/" + newProgramDiscipline.getId()))
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

        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
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
        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("program-discipline/{programDisciplineId}")
    public ResponseEntity<Object> deleteProgramDiscipline(@PathVariable("programDisciplineId") Long id){
        try {
            programDisciplineService.deleteProgramDiscipline(id);
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
    //</editor-fold>

    //<editor-fold desc="Students methods">
    @GetMapping("student")
    public ResponseEntity<Object> getAllStudents(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findAll());
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentService.findById(id));

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
                    .location(new URI("student/" + newStudent.getId()))
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
        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
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
        } catch (BadResourceException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
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
    //</editor-fold>

    //<editor-fold desc="Instructor methods">
    @GetMapping("instructor")
    public ResponseEntity<Object> getAllInstructors(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(instructorService.findAll());
    }

    @GetMapping("instructor/{instructorId}")
    public ResponseEntity<Object> getInstructor(@PathVariable("instructorId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(instructorService.findById(id));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("instructor")
    public ResponseEntity<Object> addInstructor(@RequestBody Instructor instructor) throws URISyntaxException {
        try {
            Instructor newInstructor = instructorService.addInstructor(instructor);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .location(new URI("instructor/" + newInstructor.getId()))
                    .body(newInstructor);

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

    @PutMapping("instructor/{instructorId}")
    public ResponseEntity<Object> updateInstructor(@PathVariable("instructorId") Long id,
                                                   @RequestBody Instructor instructor){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(instructorService.updateInstructor(id, instructor));

        } catch (ResourceNotFoundException e) {
            // TODO - log stack trace
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("instructor/{instructorId}")
    public ResponseEntity<Object> deleteInstructor(@PathVariable("instructorId") Long id){
        try {
            instructorService.deleteInstructor(id);
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
    //</editor-fold>

    @GetMapping("timetable")
    public ResponseEntity<Object> getAllTimetables(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(timetableService.findAll());
    }

    @GetMapping("timetable/{timetableId}")
    public ResponseEntity<Object> getTimetable(@PathVariable("timetableId") Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(timetableService.findById(id));

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
                    .location(new URI("timetable/" + newTimetable.getId()))
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

        } catch (BadResourceException | TimetableCollisionException e) {
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

        } catch (BadResourceException | TimetableCollisionException e) {
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

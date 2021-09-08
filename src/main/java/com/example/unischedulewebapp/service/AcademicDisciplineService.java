package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.AcademicDisciplineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AcademicDisciplineService {

    private final static String DSCPL_NOT_FOUND_MSG =
            "Discipline %s not found!";
    private final static String DSCPL_EXISTS_MSG =
            "Discipline %s already exists!";
    private final static String DSCPL_INSTRUCTOR_NOT_FOUND_MSG =
            "Discipline is taught by non-existent instructor!";

    private final AcademicDisciplineRepo disciplineRepository;
    private final AcademicDepartmentService departmentService;
    private final InstructorService instructorService;

    @Autowired
    public AcademicDisciplineService(AcademicDisciplineRepo disciplineRepository,
                                     AcademicDepartmentService departmentService,
                                     InstructorService instructorService) {
        this.disciplineRepository = disciplineRepository;
        this.departmentService = departmentService;
        this.instructorService = instructorService;
    }

    public boolean existsById(Long id) {
        return disciplineRepository
                .existsById(id);
    }

    public AcademicDiscipline findById(Long id) throws ResourceNotFoundException {
        return disciplineRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<AcademicDiscipline> findByName(String name) {
        return new ArrayList<>(disciplineRepository
                .findByName(name));
    }

    public List<AcademicDiscipline> findByAbbreviation(String abbreviation) {
        return new ArrayList<>(disciplineRepository
                .findByAbbreviation(abbreviation));
    }

    public List<AcademicDiscipline> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(disciplineRepository
                .findByDepartment(department));
    }

    public List<AcademicDiscipline> findByLeadingInstructor(Instructor instructor) {
        return new ArrayList<>(disciplineRepository
                .findByLeadingInstructor(instructor));
    }

    // TODO - enable once the query is reworked
//    public List<AcademicDiscipline> findByTeacher(Teacher teacher) {
//        return new ArrayList<>(disciplineRepository
//                .findByTeacher(teacher));
//    }

    public List<AcademicDiscipline> findAll() {
        return disciplineRepository
                .findAll();
    }

    public List<AcademicDiscipline> findAll(Sort sort) {
        return disciplineRepository
                .findAll(sort);
    }

    public List<AcademicDiscipline> findAll(int pageNumber, int rowsPerPage) {
        return disciplineRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public AcademicDiscipline addDiscipline(AcademicDiscipline discipline) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(discipline.getId()!=null && existsById(discipline.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(DSCPL_EXISTS_MSG, "with id=" + discipline.getId())
            );

        discipline.setDepartment(departmentService.findById(discipline.getDepartment().getId()));

        discipline.setLeadingInstructor(instructorService.findById(discipline.getLeadingInstructor().getId()));

        Set<Instructor> instructors = new HashSet<>();
        for(Instructor instructor : discipline.getAssistingInstructors())
            instructors.add(instructorService.findById(instructor.getId()));
        discipline.setAssistingInstructors(instructors);

        return disciplineRepository.save(discipline);
    }

    public AcademicDiscipline updateDiscipline(Long id, AcademicDiscipline discipline) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
            );

        discipline.setDepartment(departmentService.findById(discipline.getDepartment().getId()));

        discipline.setLeadingInstructor(instructorService.findById(discipline.getLeadingInstructor().getId()));

        Set<Instructor> instructors = new HashSet<>();
        for(Instructor instructor : discipline.getAssistingInstructors())
            instructors.add(instructorService.findById(instructor.getId()));
        discipline.setAssistingInstructors(instructors);

        discipline.setId(id);
        return disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
            );

        disciplineRepository.deleteById(id);
    }
}

package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.AcademicDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicDisciplineService {

    private final static String DSCPL_NOT_FOUND_MSG =
            "Discipline %s not found!";
    private final static String DSCPL_EXISTS_MSG =
            "Discipline %s already exists!";
    private final static String DSCPL_INSTRUCTOR_NOT_FOUND_MSG =
            "Discipline is taught by non-existent instructor!";

    private final AcademicDisciplineRepository disciplineRepository;
    private final InstructorService instructorService;

    @Autowired
    public AcademicDisciplineService(AcademicDisciplineRepository disciplineRepository,
                                     InstructorService instructorService) {
        this.disciplineRepository = disciplineRepository;
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

        if(!instructorService.existsById(discipline.getLeadingInstructor().getId()))
            throw new ResourceNotFoundException(DSCPL_INSTRUCTOR_NOT_FOUND_MSG);

        for(Instructor instructor : discipline.getAssistingInstructors())
            if(!instructorService.existsById(instructor.getId()))
                throw new ResourceNotFoundException(DSCPL_INSTRUCTOR_NOT_FOUND_MSG);

        return disciplineRepository.save(discipline);
    }

    public AcademicDiscipline updateDiscipline(Long id, AcademicDiscipline discipline) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!instructorService.existsById(discipline.getLeadingInstructor().getId()))
            throw new ResourceNotFoundException(DSCPL_INSTRUCTOR_NOT_FOUND_MSG);

        for(Instructor instructor : discipline.getAssistingInstructors())
            if(!instructorService.existsById(instructor.getId()))
                throw new ResourceNotFoundException(DSCPL_INSTRUCTOR_NOT_FOUND_MSG);

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

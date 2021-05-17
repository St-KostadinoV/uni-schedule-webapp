package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Teacher;
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
    private final static String DSCPL_TEACHER_NOT_FOUND_MSG =
            "Discipline is taught by non-existent teacher!";

    private final AcademicDisciplineRepository disciplineRepository;
    private final TeacherService teacherService;

    @Autowired
    public AcademicDisciplineService(AcademicDisciplineRepository disciplineRepository,
                                     TeacherService teacherService) {
        this.disciplineRepository = disciplineRepository;
        this.teacherService = teacherService;
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

    public List<AcademicDiscipline> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(disciplineRepository
                .findByDepartment(department));
    }

    public List<AcademicDiscipline> findByLeadingTeacher(Teacher teacher) {
        return new ArrayList<>(disciplineRepository
                .findByLeadingTeacher(teacher));
    }

    public List<AcademicDiscipline> findAll() {
        return disciplineRepository
                .findAll();
    }

    public List<AcademicDiscipline> findAll(int pageNumber, int rowsPerPage) {
        return disciplineRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public List<AcademicDiscipline> findAll(Sort sort) {
        return disciplineRepository
                .findAll(sort);
    }

    public void addDiscipline(AcademicDiscipline discipline) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(discipline.getId()!=null && existsById(discipline.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(DSCPL_EXISTS_MSG, "with id=" + discipline.getId())
            );

        if(!teacherService.existsById(discipline.getLeadingTeacher().getId()))
            throw new ResourceNotFoundException(DSCPL_TEACHER_NOT_FOUND_MSG);

        for(Teacher teacher : discipline.getAssistingTeachers()) {
            if(!teacherService.existsById(teacher.getId()))
                throw new ResourceNotFoundException(DSCPL_TEACHER_NOT_FOUND_MSG);
        }

        disciplineRepository.save(discipline);
    }

    public void updateDiscipline(Long id, AcademicDiscipline discipline) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!teacherService.existsById(discipline.getLeadingTeacher().getId()))
            throw new ResourceNotFoundException(DSCPL_TEACHER_NOT_FOUND_MSG);

        for(Teacher teacher : discipline.getAssistingTeachers()) {
            if(!teacherService.existsById(teacher.getId()))
                throw new ResourceNotFoundException(DSCPL_TEACHER_NOT_FOUND_MSG);
        }

        discipline.setId(id);
        disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DSCPL_NOT_FOUND_MSG, "with id=" + id)
            );

        disciplineRepository.deleteById(id);
    }
}

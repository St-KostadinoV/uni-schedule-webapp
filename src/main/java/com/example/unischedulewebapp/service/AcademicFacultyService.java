package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicFaculty;
import com.example.unischedulewebapp.repository.AcademicFacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicFacultyService {

    private final static String FACULTY_NOT_FOUND_MSG =
            "Faculty %s not found!";
    private final static String FACULTY_EXISTS_MSG =
            "Faculty %s already exists!";

    private final AcademicFacultyRepo facultyRepository;

    @Autowired
    public AcademicFacultyService(AcademicFacultyRepo facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public boolean existsById(Long id) {
        return facultyRepository
                .existsById(id);
    }

    public AcademicFaculty findById(Long id) throws ResourceNotFoundException {
        return facultyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(FACULTY_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<AcademicFaculty> findByName(String name) {
        return new ArrayList<>(facultyRepository
                .findByName(name));
    }

    public List<AcademicFaculty> findByAbbreviation(String abbreviation) {
        return new ArrayList<>(facultyRepository
                .findByAbbreviation(abbreviation));
    }

    public List<AcademicFaculty> findAll() {
        return facultyRepository
                .findAll();
    }

    public List<AcademicFaculty> findAll(Sort sort) {
        return facultyRepository
                .findAll(sort);
    }

    public List<AcademicFaculty> findAll(int pageNumber, int rowsPerPage) {
        return facultyRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public AcademicFaculty addFaculty(AcademicFaculty faculty) throws ResourceAlreadyExistsException {
        if(faculty.getId() != null && existsById(faculty.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(FACULTY_EXISTS_MSG, "with  id=" + faculty.getId())
            );

        return facultyRepository.save(faculty);
    }

    public AcademicFaculty updateFaculty(Long id, AcademicFaculty faculty) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(FACULTY_NOT_FOUND_MSG, "with id=" + id)
            );

        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(FACULTY_NOT_FOUND_MSG, "with id=" + id)
            );

        facultyRepository.deleteById(id);
    }
}

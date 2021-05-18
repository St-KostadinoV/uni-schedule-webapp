package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.generic.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@JsonFilter("DepartmentFilter")
@Entity
@Table(
        name = "department"
)
public class AcademicDepartment extends AcademicStructure {

    @ManyToOne
    @JoinColumn(
            name = "faculty_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicFaculty faculty;

    public AcademicDepartment() {
    }

    public AcademicDepartment(Long id,
                              String name,
                              String abbreviation,
                              AcademicFaculty faculty) {
        super(id, name, abbreviation);
        this.faculty = faculty;
    }

    public AcademicDepartment(String name,
                              String abbreviation,
                              AcademicFaculty faculty) {
        super(name, abbreviation);
        this.faculty = faculty;
    }

    public AcademicFaculty getFaculty() {
        return faculty;
    }

    public void setFaculty(AcademicFaculty faculty) {
        this.faculty = faculty;
    }
}

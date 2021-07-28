package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.base.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonFilter("DisciplineFilter")
@Entity
@Table(
        name = "discipline"
)
public class AcademicDiscipline extends AcademicStructure {

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicDepartment department;

    @ManyToOne
    @JoinColumn(
            name = "instructor_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Instructor leadingInstructor;

    @ManyToMany
    @JoinTable(
            name = "discipline_instructor",
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Instructor> assistingInstructors;

    public AcademicDiscipline() {
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Instructor leadingInstructor,
                              Set<Instructor> assistingInstructors) {
        super(name, abbreviation);
        this.department = department;
        this.leadingInstructor = leadingInstructor;
        this.assistingInstructors = assistingInstructors;
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Instructor leadingInstructor) {
        super(name, abbreviation);
        this.department = department;
        this.leadingInstructor = leadingInstructor;
        this.assistingInstructors = new HashSet<>();
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }

    public Instructor getLeadingInstructor() {
        return leadingInstructor;
    }

    public void setLeadingInstructor(Instructor leadingInstructor) {
        this.leadingInstructor = leadingInstructor;
    }

    public Set<Instructor> getAssistingInstructors() {
        return assistingInstructors;
    }

    public void setAssistingInstructors(Set<Instructor> assistingInstructors) {
        this.assistingInstructors = assistingInstructors;
    }

    public void addAssistingInstructors(Instructor instructor) {
        assistingInstructors.add(instructor);
    }

    public void removeAssistingInstructors(Instructor instructor) {
        assistingInstructors.remove(instructor);
    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(leadingInstructor);
        instructors.addAll(assistingInstructors);
        return instructors;
    }
}

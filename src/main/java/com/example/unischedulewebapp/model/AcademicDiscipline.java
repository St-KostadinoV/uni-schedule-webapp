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
            name = "teacher_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Teacher leadingTeacher;

    @ManyToMany
    @JoinTable(
            name = "discipline_teacher",
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> assistingTeachers;

    public AcademicDiscipline() {
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers) {
        super(name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher) {
        super(name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = new HashSet<>();
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }

    public Teacher getLeadingTeacher() {
        return leadingTeacher;
    }

    public void setLeadingTeacher(Teacher leadingTeacher) {
        this.leadingTeacher = leadingTeacher;
    }

    public Set<Teacher> getAssistingTeachers() {
        return assistingTeachers;
    }

    public void setAssistingTeachers(Set<Teacher> assistingTeachers) {
        this.assistingTeachers = assistingTeachers;
    }

    public void addAssistingTeachers(Teacher teacher) {
        assistingTeachers.add(teacher);
    }

    public void removeAssistingTeachers(Teacher teacher) {
        assistingTeachers.remove(teacher);
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(leadingTeacher);
        teachers.addAll(assistingTeachers);
        return teachers;
    }
}

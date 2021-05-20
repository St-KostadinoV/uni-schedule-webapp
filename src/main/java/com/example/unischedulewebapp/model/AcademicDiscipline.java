package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.generic.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

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

    @Column(
            name = "url"
    )
    private String disciplineUrl;

    public AcademicDiscipline() {
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers,
                              String disciplineUrl) {
        super(id, name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
        this.disciplineUrl = disciplineUrl;
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers) {
        super(id, name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher,
                              String disciplineUrl) {
        super(id, name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.disciplineUrl = disciplineUrl;
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher) {
        super(id, name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers,
                              String disciplineUrl) {
        super(name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
        this.disciplineUrl = disciplineUrl;
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
                              Teacher leadingTeacher,
                              String disciplineUrl) {
        super(name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
        this.disciplineUrl = disciplineUrl;
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              AcademicDepartment department,
                              Teacher leadingTeacher) {
        super(name, abbreviation);
        this.department = department;
        this.leadingTeacher = leadingTeacher;
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }

    public String getDisciplineUrl() {
        return disciplineUrl;
    }

    public void setDisciplineUrl(String disciplineUrl) {
        this.disciplineUrl = disciplineUrl;
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
}

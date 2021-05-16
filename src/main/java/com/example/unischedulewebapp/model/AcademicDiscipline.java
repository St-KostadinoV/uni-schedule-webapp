package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.generic.AcademicStructure;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(
        name = "discipline"
)
public class AcademicDiscipline extends AcademicStructure {

    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            nullable = false,
            referencedColumnName = "user_details_id"
    )
    private Teacher leadingTeacher;

    @ManyToMany
    @JoinTable(
            name = "discipline_teacher",
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> assistingTeachers;

    // TODO - finish property
    private String link;

    public AcademicDiscipline() {
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers) {
        super(id, name, abbreviation);
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
    }

    public AcademicDiscipline(Long id,
                              String name,
                              String abbreviation,
                              Teacher leadingTeacher) {
        super(id, name, abbreviation);
        this.leadingTeacher = leadingTeacher;
    }

    public AcademicDiscipline(String name,
                              String abbreviation,
                              Teacher leadingTeacher,
                              Set<Teacher> assistingTeachers) {
        super(name, abbreviation);
        this.leadingTeacher = leadingTeacher;
        this.assistingTeachers = assistingTeachers;
    }
    public AcademicDiscipline(String name,
                              String abbreviation,
                              Teacher leadingTeacher) {
        super(name, abbreviation);
        this.leadingTeacher = leadingTeacher;
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

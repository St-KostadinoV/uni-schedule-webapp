package com.example.unischedulewebapp.model;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "discipline_timetable")
public class DisciplineTimetable {

    @EmbeddedId
    private DisciplineTimetablePK id;

    @ManyToOne(
            fetch = LAZY
    )
    @MapsId("disciplineId")
    private AcademicDiscipline discipline;

    @ManyToOne(
            fetch = LAZY
    )
    @MapsId("timetableId")
    private AcademicTimetable timetable;

    @Column(
            name = "class_type"
    )
    private AcademicClassType classType;

    @Column(
            name = "student_group"
    )
    private Integer studentGroup;

    public DisciplineTimetable() {
    }

    public DisciplineTimetable(DisciplineTimetablePK id,
                               AcademicDiscipline discipline,
                               AcademicTimetable timetable,
                               AcademicClassType classType,
                               Integer studentGroup) {
        this.id = id;
        this.discipline = discipline;
        this.timetable = timetable;
        this.classType = classType;
        this.studentGroup = studentGroup;
    }

    public DisciplineTimetable(AcademicDiscipline discipline,
                               AcademicTimetable timetable,
                               AcademicClassType classType,
                               Integer studentGroup) {
        this.discipline = discipline;
        this.timetable = timetable;
        this.classType = classType;
        this.studentGroup = studentGroup;
    }

    public DisciplineTimetablePK getId() {
        return id;
    }

    public void setId(DisciplineTimetablePK id) {
        this.id = id;
    }

    public AcademicDiscipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(AcademicDiscipline discipline) {
        this.discipline = discipline;
    }

    public AcademicTimetable getTimetable() {
        return timetable;
    }

    public void setTimetable(AcademicTimetable timetable) {
        this.timetable = timetable;
    }

    public AcademicClassType getClassType() {
        return classType;
    }

    public void setClassType(AcademicClassType classType) {
        this.classType = classType;
    }

    public Integer getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Integer studentGroup) {
        this.studentGroup = studentGroup;
    }
}

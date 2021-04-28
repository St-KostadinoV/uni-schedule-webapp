package com.example.unischedulewebapp.model;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
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
    private ProgramDiscipline programDiscipline;

    @ManyToOne(
            fetch = LAZY
    )
    @MapsId("timetableId")
    private AcademicTimetable timetable;

    @Enumerated(value = STRING)
    @Column(
            name = "class_type",
            nullable = false
    )
    private AcademicClassType classType;

    @Column(
            name = "student_group"
    )
    private Integer studentGroup;

    public DisciplineTimetable() {
    }

    public DisciplineTimetable(DisciplineTimetablePK id,
                               ProgramDiscipline programDiscipline,
                               AcademicTimetable timetable,
                               AcademicClassType classType,
                               Integer studentGroup) {
        this.id = id;
        this.programDiscipline = programDiscipline;
        this.timetable = timetable;
        this.classType = classType;
        this.studentGroup = studentGroup;
    }

    public DisciplineTimetable(ProgramDiscipline programDiscipline,
                               AcademicTimetable timetable,
                               AcademicClassType classType,
                               Integer studentGroup) {
        this.programDiscipline = programDiscipline;
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

    public ProgramDiscipline getProgramDiscipline() {
        return programDiscipline;
    }

    public void setProgramDiscipline(ProgramDiscipline programDiscipline) {
        this.programDiscipline = programDiscipline;
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

package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.enums.AcademicClassType;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static javax.persistence.GenerationType.IDENTITY;

@JsonFilter("TimetableFilter")
@Entity
@Table(
        name = "timetable"
)
public class AcademicTimetable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "instructor_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Instructor assignedInstructor;

    @Column(
            name = "day",
            nullable = false
    )
    private DayOfWeek dayOfWeek;

    @Column(
            name = "start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            nullable = false
    )
    private LocalTime endTime;

    @Column(
            name = "room",
            nullable = false
    )
    private String designatedRoom;

    @ManyToOne
    @JoinColumn(
            name = "program_discipline_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private ProgramDiscipline programDiscipline;

    @Column(
            name = "class_type",
            nullable = false
    )
    private AcademicClassType classType;

    @Column(
            name = "student_group"
    )
    private Integer studentGroup;

    public AcademicTimetable() {
    }

    public AcademicTimetable(Instructor assignedInstructor,
                             DayOfWeek dayOfWeek,
                             LocalTime startTime,
                             LocalTime endTime,
                             String designatedRoom,
                             ProgramDiscipline programDiscipline,
                             AcademicClassType classType,
                             Integer studentGroup) {
        this.assignedInstructor = assignedInstructor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.designatedRoom = designatedRoom;
        this.programDiscipline = programDiscipline;
        this.classType = classType;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instructor getAssignedInstructor() {
        return assignedInstructor;
    }

    public void setAssignedInstructor(Instructor assignedInstructor) {
        this.assignedInstructor = assignedInstructor;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDesignatedRoom() {
        return designatedRoom;
    }

    public void setDesignatedRoom(String designatedRoom) {
        this.designatedRoom = designatedRoom;
    }

    public ProgramDiscipline getProgramDiscipline() {
        return programDiscipline;
    }

    public void setProgramDiscipline(ProgramDiscipline programDiscipline) {
        this.programDiscipline = programDiscipline;
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

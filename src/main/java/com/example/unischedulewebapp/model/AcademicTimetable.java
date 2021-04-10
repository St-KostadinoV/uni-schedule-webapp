package com.example.unischedulewebapp.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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

    @ManyToOne(
            fetch = LAZY
    )
    @JoinColumn(
            name = "teacher_id",
            nullable = false,
            referencedColumnName = "user_details_id"
    )
    private Teacher assignedTeacher;

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

    public AcademicTimetable() {
    }

    public AcademicTimetable(Long id,
                             Teacher assignedTeacher,
                             DayOfWeek dayOfWeek,
                             LocalTime startTime,
                             LocalTime endTime,
                             String designatedRoom) {
        this.id = id;
        this.assignedTeacher = assignedTeacher;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.designatedRoom = designatedRoom;
    }

    public AcademicTimetable(Teacher assignedTeacher,
                             DayOfWeek dayOfWeek,
                             LocalTime startTime,
                             LocalTime endTime,
                             String designatedRoom) {
        this.assignedTeacher = assignedTeacher;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.designatedRoom = designatedRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
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
}

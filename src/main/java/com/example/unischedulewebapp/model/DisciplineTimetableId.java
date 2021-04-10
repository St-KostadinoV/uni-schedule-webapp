package com.example.unischedulewebapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DisciplineTimetableId implements Serializable {

    @Column(
            name = "discipline_id"
    )
    private Long disciplineId;

    @Column(
            name = "timetable_id"
    )
    private Long timetableId;


    public DisciplineTimetableId() {
    }

    public DisciplineTimetableId(Long disciplineId, Long timetableId) {
        this.disciplineId = disciplineId;
        this.timetableId = timetableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplineTimetableId that = (DisciplineTimetableId) o;
        return Objects.equals(disciplineId, that.disciplineId) && Objects.equals(timetableId, that.timetableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplineId, timetableId);
    }
}

package com.example.unischedulewebapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DisciplineTimetablePK implements Serializable {

    @Column(
            name = "program_discipline_id"
    )
    private Long programDisciplineId;

    @Column(
            name = "timetable_id"
    )
    private Long timetableId;


    public DisciplineTimetablePK() {
    }

    public DisciplineTimetablePK(Long programDisciplineId, Long timetableId) {
        this.programDisciplineId = programDisciplineId;
        this.timetableId = timetableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplineTimetablePK that = (DisciplineTimetablePK) o;
        return Objects.equals(programDisciplineId, that.programDisciplineId) && Objects.equals(timetableId, that.timetableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programDisciplineId, timetableId);
    }
}

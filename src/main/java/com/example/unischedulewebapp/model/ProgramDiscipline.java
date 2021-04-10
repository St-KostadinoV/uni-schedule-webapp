package com.example.unischedulewebapp.model;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name = "program_discipline"
)
public class ProgramDiscipline {

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
            name = "program_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicProgram program;

    @ManyToOne(
            fetch = LAZY
    )
    @JoinColumn(
            name = "discipline_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicDiscipline discipline;

    @Column(
            name = "year",
            nullable = false
    )
    private Integer academicYear;

    public ProgramDiscipline() {
    }

    public ProgramDiscipline(Long id,
                             AcademicProgram program,
                             AcademicDiscipline discipline,
                             Integer academicYear) {
        this.id = id;
        this.program = program;
        this.discipline = discipline;
        this.academicYear = academicYear;
    }

    public ProgramDiscipline(AcademicProgram program,
                             AcademicDiscipline discipline,
                             Integer academicYear) {
        this.program = program;
        this.discipline = discipline;
        this.academicYear = academicYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicProgram getProgram() {
        return program;
    }

    public void setProgram(AcademicProgram program) {
        this.program = program;
    }

    public AcademicDiscipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(AcademicDiscipline discipline) {
        this.discipline = discipline;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }
}

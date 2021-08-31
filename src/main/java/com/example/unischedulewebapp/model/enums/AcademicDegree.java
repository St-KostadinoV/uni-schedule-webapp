package com.example.unischedulewebapp.model.enums;

public enum AcademicDegree {
    BACHELOR("бакалавър"),
    MASTER("магистър"),
    DOCTOR("доктор"),
    DOCTOR_OF_SCIENCE("доктор на науките");

    private final String name;

    AcademicDegree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

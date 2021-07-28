package com.example.unischedulewebapp.model.enums;

public enum AcademicDegree {
    BACHELORS("бакалавър"),
    MASTERS("магистър");

    private final String name;

    AcademicDegree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

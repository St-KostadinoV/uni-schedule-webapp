package com.example.unischedulewebapp.model.enums;

public enum ProfessionalQualification {
    ENGINEER("инженер");

    private final String name;

    ProfessionalQualification(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

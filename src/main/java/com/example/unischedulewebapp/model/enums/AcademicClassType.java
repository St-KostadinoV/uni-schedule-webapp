package com.example.unischedulewebapp.model.enums;

public enum AcademicClassType {
    LECTURE("лекция"),
    LAB_TUTORIAL("лабораторно упражнение"),
    SEMINAR("семинарно упражнение"),
    EDU_PRACTICE("учебна практика");

    private String name;

    AcademicClassType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

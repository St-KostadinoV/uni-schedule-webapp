package com.example.unischedulewebapp.model.enums;

public enum AcademicClassType {
    LECTURE("лекция"),
    LAB_TUTORIAL("лабораторно упражнение"),
    SEMINAR("семинарно упражнение"),
    EDU_PRACTICE("учебна практика");

    private String classType;

    AcademicClassType(String classType) {
        this.classType = classType;
    }

    public String getClassType() {
        return classType;
    }
}

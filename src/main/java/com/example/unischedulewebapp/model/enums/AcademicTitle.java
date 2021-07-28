package com.example.unischedulewebapp.model.enums;

public enum AcademicTitle {
    PROFESSOR("професор", "проф."),
    ASSOCIATE_PROFESSOR("доцент", "доц."),
    SENIOR_ASSISTANT_PROFESSOR("главен асистент", "гл. ас."),
    ASSISTANT_PROFESSOR("асистент", "ас."),
    SENIOR_INSTRUCTOR("старши преподавател", "ст. пр."),
    INSTRUCTOR("преподавател", "пр.");

    private final String name;
    private final String abbreviation;

    AcademicTitle(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}

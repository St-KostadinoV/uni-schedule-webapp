package com.example.unischedulewebapp.model.enums;

public enum AcademicTitle {
    PROFESSOR("проф."),                     //  професор
    ASSOCIATE_PROFESSOR("доц."),            //  доцент
    SENIOR_ASSISTANT_PROFESSOR("гл. ас."),  //  главен асистент
    ASSISTANT_PROFESSOR("ас."),             //  асистент
    SENIOR_TEACHER("ст. пр."),              //  старши преподавател
    TEACHER("пр.");                         //  преподавател

    private final String title;

    AcademicTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

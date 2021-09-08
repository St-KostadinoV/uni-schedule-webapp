export function getInstructorTitle(title) {
    switch(title){
        case 'PROFESSOR':
            return 'професор';
        case 'ASSOCIATE_PROFESSOR':
            return 'доцент';
        case 'SENIOR_ASSISTANT_PROFESSOR':
            return 'главен асистент';
        case 'ASSISTANT_PROFESSOR':
            return 'асистент';
        case 'SENIOR_INSTRUCTOR':
            return 'старши преподавател';
        case 'INSTRUCTOR':
            return 'преподавател';
    }
}

export function getInstructorShortTitle(title) {
    switch(title){
        case 'PROFESSOR':
            return 'проф.';
        case 'ASSOCIATE_PROFESSOR':
            return 'доц.';
        case 'SENIOR_ASSISTANT_PROFESSOR':
            return 'гл. ас.';
        case 'ASSISTANT_PROFESSOR':
            return 'ас.';
        case 'SENIOR_INSTRUCTOR':
            return 'ст. пр.';
        case 'INSTRUCTOR':
            return 'пр.';
    }
}

export function getShortAcademicDegree(degree) {
    switch(degree){
        case 'MASTER':
            return 'маг.';
        case 'DOCTOR':
            return 'д-р';
        case 'DOCTOR_OF_SCIENCE':
            return 'дн';
    }
}

export function getAcademicDegree(degree) {
    switch(degree){
        case 'BACHELOR':
            return 'бакалавър';
        case 'MASTER':
            return 'магистър';
        case 'DOCTOR':
            return 'доктор';
        case 'DOCTOR_OF_SCIENCE':
            return 'доктор на науките';
    }
}

export function getQualification(qualification) {
    switch(qualification){
        case 'ENGINEER':
            return 'инж.';
        default:
            return '';
    }
}

export function getClassType(classType) {
    switch(classType){
        case 'LECTURE':
            return 'лекция';
        case 'LAB_TUTORIAL':
            return 'лабораторно упражнение';
        case 'SEMINAR':
            return 'семинарно упражнение';
        case 'EDU_PRACTICE':
            return 'учебна практика';
    }
}

export function getToday() {
    let d = new Date()
    switch (d.getDay()) {
        case 1:
            return 'MONDAY';
        case 2:
            return 'TUESDAY';
        case 3:
            return 'WEDNESDAY';
        case 4:
            return 'THURSDAY';
        case 5:
            return 'FRIDAY';
        case 6:
            return 'SATURDAY';
        case 0:
            return 'SUNDAY';
    }
}

export function getDayName(day) {
    switch (day) {
        case 'MONDAY':
            return 'Понеделник';
        case 'TUESDAY':
            return 'Вторник';
        case 'WEDNESDAY':
            return 'Сряда';
        case 'THURSDAY':
            return 'Четвъртък';
        case 'FRIDAY':
            return 'Петък';
        case 'SATURDAY':
            return 'Събота';
        case 'SUNDAY':
            return 'Неделя';
    }
}
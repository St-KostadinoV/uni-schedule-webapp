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

export function getAcademicDegree(degree) {
    switch(degree){
        case 'BACHELORS':
            return 'бакалавър';
        case 'MASTERS':
            return 'магистър';
        case 'DOCTORS':
            return 'доктор';
    }
}
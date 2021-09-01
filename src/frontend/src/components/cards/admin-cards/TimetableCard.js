import {getClassType, getDay, getInstructorShortTitle, getQualification, getShortAcademicDegree} from "../../../util";
import EditCard from "./EditCard";

const TimetableCard = ({ timetable }) => {
    return (
        <EditCard>
            <h4>{timetable.programDiscipline.discipline.name}</h4>
            <p className='card-label'><i>{getClassType(timetable.classType)}</i></p>
            <p className='card-label'><b>Преподавател: </b>{getInstructorShortTitle(timetable.assignedInstructor.title)} {getShortAcademicDegree(timetable.assignedInstructor.degree)} {getQualification(timetable.assignedInstructor.qualification)} {timetable.assignedInstructor.firstName} {timetable.assignedInstructor.lastName}</p>
            <p className='card-label'><b>Преподава на: </b>{timetable.programDiscipline.program.abbreviation}, {timetable.programDiscipline.academicYear} курс{timetable.studentGroup && ', ' + timetable.studentGroup + ' група'}</p>
            <p className='card-label'><b>Ден и час: </b>{getDay(timetable.dayOfWeek)}, {timetable.startTime.slice(0,-3)} - {timetable.endTime.slice(0,-3)}</p>
            <p className='card-label'><b>Кабинет: </b>{timetable.designatedRoom}</p>
        </EditCard>
    )
}

export default TimetableCard
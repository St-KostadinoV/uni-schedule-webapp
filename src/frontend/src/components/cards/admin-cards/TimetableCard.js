import {getClassType, getDayName, getInstructorShortTitle, getQualification, getShortAcademicDegree} from "../../../util";
import EditCard from "./EditCard";

const TimetableCard = ({ timetable, onDelete, onEdit }) => {
    return (
        <EditCard itemId={timetable.id} onDelete={onDelete} onEdit={onEdit}>
            <h4>{timetable.programDiscipline.discipline.name}</h4>
            <p><i>{getClassType(timetable.classType)}</i></p>
            <p><b>Преподавател: </b>{getInstructorShortTitle(timetable.assignedInstructor.title)} {getShortAcademicDegree(timetable.assignedInstructor.degree)} {getQualification(timetable.assignedInstructor.qualification)} {timetable.assignedInstructor.firstName} {timetable.assignedInstructor.lastName}</p>
            <p><b>Преподава на: </b>{timetable.programDiscipline.program.abbreviation}, {timetable.programDiscipline.academicYear} курс{timetable.studentGroup && ', ' + timetable.studentGroup + ' група'}</p>
            <p><b>Ден и час: </b>{getDayName(timetable.dayOfWeek)}, {timetable.startTime.slice(0,-3)} - {timetable.endTime.slice(0,-3)}</p>
            <p><b>Кабинет: </b>{timetable.designatedRoom}</p>
        </EditCard>
    )
}

export default TimetableCard
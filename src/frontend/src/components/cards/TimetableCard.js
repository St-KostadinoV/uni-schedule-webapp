import Card from "./Card";
import { getClassType, getShortAcademicDegree, getInstructorShortTitle, getQualification} from "../../util";

const TimetableCard = ({ timetable }) => {
    return (
        <Card>
            {timetable.assignedInstructor ? (
                <div className='card-content-timetable'>
                    <div className='card-sub-1'>
                        <p>{timetable.startTime.slice(0,-3)} - {timetable.endTime.slice(0,-3)}</p>
                        <p className='card-timetable-label'>{timetable.designatedRoom}</p>
                    </div>
                    <div className='card-sub-2'>
                        <p><i>{getClassType(timetable.classType)}</i></p>
                        <p className='card-timetable-label'>{timetable.programDiscipline.discipline.name}</p>
                        <p className='card-timetable-label'>{getInstructorShortTitle(timetable.assignedInstructor.title)} {getShortAcademicDegree(timetable.assignedInstructor.degree)} {getQualification(timetable.assignedInstructor.qualification)} {timetable.assignedInstructor.firstName} {timetable.assignedInstructor.lastName}</p>
                    </div>
                </div>
            ) : (
                <div className='card-content-timetable'>
                    <div className='card-sub-1'>
                        <p>{timetable.startTime.slice(0,-3)} - {timetable.endTime.slice(0,-3)}</p>
                        <p className='card-timetable-label'>{timetable.designatedRoom}</p>
                    </div>
                    <div className='card-sub-2'>
                        <p><i>{getClassType(timetable.classType)}</i></p>
                        <p className='card-timetable-label'>{timetable.programDiscipline.discipline.name}</p>
                        <p className='card-timetable-label'>{timetable.programDiscipline.program.abbreviation}, {timetable.programDiscipline.academicYear} курс{timetable.studentGroup && ', ' + timetable.studentGroup + ' група'}</p>
                    </div>
                </div>
            )}
        </Card>
    )
}

export default TimetableCard;
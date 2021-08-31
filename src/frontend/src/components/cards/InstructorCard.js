import { Link } from 'react-router-dom'
import Card from './Card'
import {getInstructorTitle, getQualification, getShortAcademicDegree} from '../../util.js'

const InstructorCard = ({ instructor }) => {
    return (
        <Link to={'/instructor/' + instructor.id}>
            <Card>
                {instructor.department ? (
                    <div className='card-content-variant-2'>
                        <span className="card-title"><p><b>{getShortAcademicDegree(instructor.degree)} {getQualification(instructor.qualification)} {instructor.firstName} {instructor.middleName} {instructor.lastName}</b></p></span>
                        <span className="card-sub-1"><p>{instructor.honoraryStatus ? 'хоноруван ' : ''}{getInstructorTitle(instructor.title)}</p></span>
                        <span className="card-sub-2"><p>Катедра: {instructor.department.abbreviation}</p></span>
                        {instructor.office && <span className="card-sub-3"><p>Кабинет: {instructor.office}</p></span>}
                    </div>
                ) : (
                    <div className='card-content-variant-1'>
                        <span className="card-title"><p><b>{getShortAcademicDegree(instructor.degree)} {getQualification(instructor.qualification)} {instructor.firstName} {instructor.middleName} {instructor.lastName}</b></p></span>
                        <span className="card-sub-1"><p>{instructor.honoraryStatus ? 'хоноруван ' : ''}{getInstructorTitle(instructor.title)}</p></span>
                        {instructor.office && <span className="card-sub-3"><p>Кабинет: {instructor.office}</p></span>}
                    </div>
                )}
            </Card>
        </Link>
    )
}

export default InstructorCard
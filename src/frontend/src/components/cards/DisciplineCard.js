import { Link } from 'react-router-dom'
import Card from './Card'
import {getInstructorShortTitle, getQualification, getShortAcademicDegree} from '../../util.js'

const DisciplineCard = ({ discipline, year }) => {
    let component;
    if(year) {
        component = (
            <div className='card-content-variant-4'>
                <span className='card-title'><p><b>{discipline.name}</b></p></span>
                <span className='card-sub-1'><p>{discipline.abbreviation}</p></span>
                <span className='card-sub-4'><p>Обучаваща катедра: {discipline.department.abbreviation}</p></span>
                <span className='card-sub-3'><p>Курс: {year}</p></span>
                <span className='card-sub-2'><p>Водещ преподавател: {discipline.leadingInstructor.honoraryStatus ? 'х. ' : ''}{getInstructorShortTitle(discipline.leadingInstructor.title)} {getShortAcademicDegree(discipline.leadingInstructor.degree)} {getQualification(discipline.leadingInstructor.qualification)} {discipline.leadingInstructor.firstName} {discipline.leadingInstructor.lastName}</p></span>
            </div>
        )
    } else if(!discipline.department) {
        component = (
            <div className='card-content-variant-1'>
                <span className='card-title'><p><b>{discipline.name}</b></p></span>
                <span className='card-sub-1'><p>{discipline.abbreviation}</p></span>
                <span className='card-sub-3'><p>Водещ преподавател: {discipline.leadingInstructor.honoraryStatus ? 'х. ' : ''}{getInstructorShortTitle(discipline.leadingInstructor.title)} {getShortAcademicDegree(discipline.leadingInstructor.degree)} {getQualification(discipline.leadingInstructor.qualification)} {discipline.leadingInstructor.firstName} {discipline.leadingInstructor.lastName}</p></span>
            </div>
        )
    } else if(!discipline.leadingInstructor) {
        component = (
            <div className='card-content-variant-1'>
                <span className='card-title'><p><b>{discipline.name}</b></p></span>
                <span className='card-sub-1'><p>{discipline.abbreviation}</p></span>
                <span className='card-sub-3'><p>Обучаваща катедра: {discipline.department.abbreviation}</p></span>
            </div>
        )
    }
    else {
        component = (
            <div className='card-content-variant-3'>
                <span className='card-title'><p><b>{discipline.name}</b></p></span>
                <span className='card-sub-1'><p>{discipline.abbreviation}</p></span>
                <span className='card-sub-2'><p>Водещ преподавател: {discipline.leadingInstructor.honoraryStatus ? 'х. ' : ''}{getInstructorShortTitle(discipline.leadingInstructor.title)} {getShortAcademicDegree(discipline.leadingInstructor.degree)} {getQualification(discipline.leadingInstructor.qualification)} {discipline.leadingInstructor.firstName} {discipline.leadingInstructor.lastName}</p></span>
                <span className='card-sub-3'><p>Обучаваща катедра: {discipline.department.abbreviation}</p></span>
            </div>
        )
    }

    return (
        <Link to={'/discipline/' + discipline.id}>
            <Card>
                {component}
            </Card>
        </Link>
    )
}

export default DisciplineCard

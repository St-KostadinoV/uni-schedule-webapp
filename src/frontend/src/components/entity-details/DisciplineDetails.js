import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import InstructorCard from '../cards/InstructorCard';
import {getInstructorShortTitle, getQualification, getShortAcademicDegree} from '../../util.js'
import FilterForm from "../forms/FilterForm";

const DisciplineDetails = () => {
    let { id } = useParams();
    const [discipline, setDiscipline] = useState([])
    const [instructors, setInstructors] = useState([])

    useEffect(() => {
        const getDiscipline = async () => {
            const disciplineFromServer = await fetchDiscipline(id)
            setDiscipline(disciplineFromServer)
        }

        const getDisciplineInstructors = async () => {
            const instructorsFromServer = await fetchDisciplineInstructors(id)
            setInstructors(instructorsFromServer)
        }

        getDiscipline()
        getDisciplineInstructors()
    }, [])

    const fetchDiscipline = async (id) => {
        const res = await fetch('http://localhost:8080/disciplines/' + id)
        const data = await res.json()
        
        return data
    }

    const fetchDisciplineInstructors = async (id) => {
        const res = await fetch('http://localhost:8080/disciplines/' + id + '/instructors')
        const data = await res.json()

        return data
    }

    return (
        <>
            <h2 className='alt'>{discipline.name}</h2>
            <h4 className='alt'><b>Абревиатура: </b>{discipline.abbreviation}</h4>
            <h4 className='alt'><b>Обучаваща катедра: </b>{discipline.department && discipline.department.name}</h4>
            <h4 className='alt'><b>Водещ преподавател: </b>{discipline.leadingInstructor && ((discipline.leadingInstructor.honoraryStatus ? 'х. ' : '') + getInstructorShortTitle(discipline.leadingInstructor.title) + ' ' + getShortAcademicDegree(discipline.leadingInstructor.degree) + ' ' + getQualification(discipline.leadingInstructor.qualification) + ' ' + discipline.leadingInstructor.firstName + ' ' + discipline.leadingInstructor.middleName + ' ' + discipline.leadingInstructor.lastName)}</h4>
            {instructors.length > 0 && <FilterForm><h3>Преподаватели</h3></FilterForm>}
            {
                instructors.map( instructor => (
                    <InstructorCard
                        key={instructor.id}
                        instructor={instructor}
                    />
                ))
            }
        </>
    )
}

export default DisciplineDetails

import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import DisciplineCard from '../cards/DisciplineCard'
import { getInstructorTitle } from '../../util.js'

const InstructorDetails = () => {
    let { id } = useParams();
    const [instructor, setInstructor] = useState([])
    const [disciplines, setDisciplines] = useState([])

    useEffect(() => {
        const getInstructor = async () => {
            const instructorFromServer = await fetchInstructor(id)
            setInstructor(instructorFromServer)
        }

        const getInstructorDisciplines = async () => {
            const disciplinesFromServer = await fetchInstructorDisciplines(id)
            setDisciplines(disciplinesFromServer)
        }

        getInstructor()
        getInstructorDisciplines()
    }, [])

    const fetchInstructor = async (id) => {
        const res = await fetch('http://localhost:8080/instructors/' + id)
        const data = await res.json()
        
        return data
    }

    const fetchInstructorDisciplines = async (id) => {
        const res = await fetch('http://localhost:8080/instructors/' + id + '/disciplines')
        const data = await res.json()

        return data
    }

    return (
        <div className='centered-content'>
            <h2 className='alt'>{instructor.firstName} {instructor.middleName} {instructor.lastName}</h2>
            <h4 className='alt'><b>Академична титла: </b>{getInstructorTitle(instructor.title)}</h4>
            <h4 className='alt'><b>Член на катедра: </b>{instructor.department && instructor.department.name}</h4>
            <h4 className='alt'><b>Кабинет: </b>{instructor.office}</h4>
            <h4 className='alt'><b>E-mail адрес: </b>{instructor.email}</h4>
            <h4 className='alt'><b>Телефон за връзка: </b>{instructor.phone}</h4>
            {disciplines.length > 0 && <h3 className='alt'>Водени дисциплини</h3>}
            {
                disciplines.map( discipline => (
                    <DisciplineCard
                        key={discipline.id}
                        discipline={discipline}
                    />
                ))
            }
        </div>
    )
}

export default InstructorDetails

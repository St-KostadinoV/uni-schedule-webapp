import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import DisciplineCard from '../cards/DisciplineCard'
import {getInstructorTitle, getQualification, getShortAcademicDegree} from '../../util.js'
import TimetableCard from "../cards/TimetableCard";
import FilterForm from "../forms/FilterForm";
import Form from "../forms/Form";

const InstructorDetails = () => {
    let { id } = useParams();
    const [instructor, setInstructor] = useState([])
    const [disciplines, setDisciplines] = useState([])
    const [timetable, setTimetable] = useState([])
    const [day, setDay] = useState('none')

    useEffect(() => {
        const getInstructor = async () => {
            const instructorFromServer = await fetchInstructor(id)
            setInstructor(instructorFromServer)
        }

        const getInstructorDisciplines = async () => {
            const disciplinesFromServer = await fetchInstructorDisciplines(id)
            setDisciplines(disciplinesFromServer)
        }

        const getInstructorTimetable = async () => {
            const timetableFromServer = await fetchInstructorTimetable(id)
            setTimetable(timetableFromServer)
        }

        getInstructor()
        getInstructorDisciplines()
        getInstructorTimetable()
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
    const fetchInstructorTimetable = async (id) => {
        const res = await fetch('http://localhost:8080/instructors/' + id + '/timetable')
        const data = await res.json()

        return data
    }

    return (
        <>
            <h2 className='alt'>{getShortAcademicDegree(instructor.degree)} {getQualification(instructor.qualification)} {instructor.firstName} {instructor.middleName} {instructor.lastName}</h2>
            <h4 className='alt'><b>Академична длъжност: </b>{getInstructorTitle(instructor.title)}</h4>
            <h4 className='alt'><b>Член на катедра: </b>{instructor.department && instructor.department.name}</h4>
            <h4 className='alt'><b>Кабинет: </b>{instructor.office}</h4>
            <h4 className='alt'><b>E-mail адрес: </b>{instructor.email}</h4>
            <h4 className='alt'><b>Телефон за връзка: </b>{instructor.phone}</h4>
            {timetable.length > 0 && (
                <>
                    <FilterForm>
                        <h3>Седмичен разпис</h3>
                        <select name='days' id='days' onChange={event => {setDay(event.target.value)}}>
                            <option value='none'> - Изберете ден - </option>
                            <option value='MONDAY'>Понеделник</option>
                            <option value='TUESDAY'>Вторник</option>
                            <option value='WEDNESDAY'>Сряда</option>
                            <option value='THURSDAY'>Четвъртък</option>
                            <option value='FRIDAY'>Петък</option>
                            <option value='SATURDAY'>Събота</option>
                            <option value='SUNDAY'>Неделя</option>
                        </select>
                    </FilterForm>
                    {
                        timetable.filter(tt => tt.dayOfWeek === day).length > 0 ? (
                        timetable
                            .filter(tt => tt.dayOfWeek === day)
                            .map(tt => (
                                <TimetableCard
                                    key={tt.id}
                                    timetable={tt}
                                />
                            ))
                        ) : (
                          day !== 'none' && <h4 className='alt'>Преподавателят няма занятия в този ден.</h4>
                        )
                    }
                </>
            )}

            {disciplines.length > 0 && <FilterForm><h3>Водени дисциплини</h3></FilterForm>}
            {
                disciplines.map( discipline => (
                    <DisciplineCard
                        key={discipline.id}
                        discipline={discipline}
                    />
                ))
            }
        </>
    )
}

export default InstructorDetails

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
            <h4 className='alt'><b>???????????????????? ????????????????: </b>{getInstructorTitle(instructor.title)}</h4>
            <h4 className='alt'><b>???????? ???? ??????????????: </b>{instructor.department && instructor.department.name}</h4>
            <h4 className='alt'><b>??????????????: </b>{instructor.office}</h4>
            <h4 className='alt'><b>E-mail ??????????: </b>{instructor.email}</h4>
            <h4 className='alt'><b>?????????????? ???? ????????????: </b>{instructor.phone}</h4>
            {timetable.length > 0 && (
                <>
                    <FilterForm>
                        <h3>???????????????? ????????????</h3>
                        <select name='days' id='days' onChange={event => {setDay(event.target.value)}}>
                            <option value='none'> - ???????????????? ?????? - </option>
                            <option value='MONDAY'>????????????????????</option>
                            <option value='TUESDAY'>??????????????</option>
                            <option value='WEDNESDAY'>??????????</option>
                            <option value='THURSDAY'>??????????????????</option>
                            <option value='FRIDAY'>??????????</option>
                            <option value='SATURDAY'>????????????</option>
                            <option value='SUNDAY'>????????????</option>
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
                          day !== 'none' && <h4 className='alt'>???????????????????????????? ???????? ?????????????? ?? ???????? ??????.</h4>
                        )
                    }
                </>
            )}

            {disciplines.length > 0 && <FilterForm><h3>???????????? ????????????????????</h3></FilterForm>}
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

import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import TimetableCard from '../cards/TimetableCard'
import FilterForm from "../forms/FilterForm";
import authService from "../../services/auth-service";

const StudentDetails = () => {
    let { id } = useParams();
    const [student, setStudent] = useState([])
    const [timetable, setTimetable] = useState([])
    const [day, setDay] = useState('none')

    const currentUser = authService.getCurrentUser();

    useEffect(() => {
        const getStudent = async () => {
            const studentFromServer = await fetchStudent(id)
            setStudent(studentFromServer)
        }
        const getStudentTimetable = async () => {
            const timetableFromServer = await fetchStudentTimetable(id)
            setTimetable(timetableFromServer)
        }

        getStudent()
        getStudentTimetable()
    }, [])

    const fetchStudent = async (id) => {
        const res = await fetch('http://localhost:8080/students/' + id)
        const data = await res.json()
        
        return data
    }
    const fetchStudentTimetable = async (id) => {
        const res = await fetch('http://localhost:8080/students/' + id + '/timetable')
        const data = await res.json()

        return data
    }

    const isContactInfoVisible = () => {
        return (!currentUser || currentUser.roles[0] === 'ROLE_STUDENT');
    }

    return (
        <>
            <h2 className='alt'>{student.firstName} {student.middleName} {student.lastName}</h2>
            <h4 className='alt'><b>Факултетен номер: </b>{student.facultyNumber}</h4>
            <h4 className='alt'><b>Специалност: </b>{student.academicProgram && student.academicProgram.name}</h4>
            <h4 className='alt'><b>Курс: </b>{student.academicYear}&nbsp;&nbsp;&nbsp;&nbsp;<b>Група: </b>{student.studentGroup}</h4>
            <h4 className='alt' style={{display: isContactInfoVisible() && 'none' }}><b>E-mail адрес: </b>{student.email}</h4>
            <h4 className='alt' style={{display: isContactInfoVisible() && 'none' }}><b>Телефон за връзка: </b>{student.phone}</h4>
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
                            day !== 'none' && <h4 className='alt'>Студентът няма занятия в този ден.</h4>
                        )
                    }
                </>
            )}

        </>
    )
}

export default StudentDetails

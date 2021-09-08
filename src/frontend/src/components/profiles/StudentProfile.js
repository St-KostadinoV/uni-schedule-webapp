import ChangePassForm from "../forms/ChangePassForm";
import {useEffect, useState} from "react";
import authHeader from "../../services/auth-header";
import TimetableCard from "../cards/TimetableCard";
import {getToday} from "../../util";
import FilterForm from "../forms/FilterForm";

const StudentProfile = ({ student }) => {
    const [timetable, setTimetable] = useState([]);
    const [day, setDay] = useState('MONDAY')
    const [showPassFrom, setShowPassForm] = useState(false);
    const [showTimetable, setShowTimetable] = useState(false);
    const [showDailyTimetable, setShowDailyTimetable] = useState(false);

    useEffect(() => {
        const getTimetable = async () => {
            const timetableFromServer = await fetchTimetable()
            setTimetable(timetableFromServer)
        }

        getTimetable()
    }, [])

    const fetchTimetable = async () => {
        const res = await fetch('http://localhost:8080/profile/timetable', {headers: authHeader()})
        const data = await res.json()

        return data
    }

    const displayPassForm = () => {
        setShowPassForm(!showPassFrom)
        setShowDailyTimetable(false)
        setShowTimetable(false)
    }

    const displayDailyTimetable = () => {
        setShowPassForm(false)
        setShowDailyTimetable(!showDailyTimetable)
        setShowTimetable(false)
    }

    const displayTimetable = () => {
        setShowPassForm(false)
        setShowDailyTimetable(false)
        setShowTimetable(!showTimetable)
    }

    const filterTimetable = (day) => {
        return timetable
            .filter( timetable => timetable.dayOfWeek === day)
    }

    return (
        <>
            <div className='side-content'>
                <h2 className='alt'><b>{student.firstName} {student.middleName} {student.lastName}</b></h2>
                <h4 className='alt'><b>Факултетен номер: </b>{student.facultyNumber}</h4>
                <h4 className='alt'><b>Специалност: </b>{student.academicProgram && student.academicProgram.name}</h4>
                <p className='alt'><b>Курс: </b>{student.academicYear}</p>
                <p className='alt'><b>Група: </b>{student.studentGroup}</p>
                <p className='alt'><b>Активен: </b>{student.activeStatus ? 'Да' : 'Не'}</p>
                <div className='contact-info'>
                    <p className='alt'><b>E-mail адрес: </b>{student.email}</p>
                    <p className='alt'><b>Телефон за връзка: </b>{student.phone}</p>
                </div>
                <div className='profile-buttons'>
                    <button className={showPassFrom ? 'selected' : ''} onClick={() => displayPassForm()} >Смени парола</button>
                    <button className={showDailyTimetable ? 'selected' : ''} onClick={() => displayDailyTimetable()}>Дневен разпис</button>
                    <button className={showTimetable ? 'selected' : ''} onClick={() => displayTimetable()}>Седмичен разпис</button>
                </div>
                {showPassFrom && <ChangePassForm onSubmit={() => setShowPassForm(false)}/>}
                {showDailyTimetable && (
                    <>
                        <FilterForm>
                            <h3>Дневен разпис</h3>
                        </FilterForm>
                        {filterTimetable(getToday()).length > 0 ? (
                            filterTimetable(getToday())
                            .map( timetable => (
                                <TimetableCard
                                    key={timetable.id}
                                    timetable={timetable}
                                />
                            ))
                        ) : (
                            <h4 className='alt'>Нямате занятия днес.</h4>
                        )}
                    </>
                )}
                {showTimetable && (
                    <>
                        <FilterForm>
                            <h3>Седмичен разпис</h3>
                            <select name='days' id='days' onChange={event => {setDay(event.target.value)}}>
                                <option value='MONDAY'>Понеделник</option>
                                <option value='TUESDAY'>Вторник</option>
                                <option value='WEDNESDAY'>Сряда</option>
                                <option value='THURSDAY'>Четвъртък</option>
                                <option value='FRIDAY'>Петък</option>
                                <option value='SATURDAY'>Събота</option>
                                <option value='SUNDAY'>Неделя</option>
                            </select>
                        </FilterForm>
                        {filterTimetable(day).length > 0 ? (
                            filterTimetable(day)
                                .map( timetable => (
                                    <TimetableCard
                                        key={timetable.id}
                                        timetable={timetable}
                                    />
                                ))
                        ) : (
                            <h4 className='alt'>Нямате занятия за избрания ден.</h4>
                        )}
                    </>
                )}
            </div>
        </>
    )
}

export default StudentProfile
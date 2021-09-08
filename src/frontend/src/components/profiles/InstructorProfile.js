import {getInstructorTitle, getQualification, getShortAcademicDegree, getToday} from "../../util";
import ChangePassForm from "../forms/ChangePassForm";
import {useEffect, useState} from "react";
import FilterForm from "../forms/FilterForm";
import TimetableCard from "../cards/TimetableCard";
import authHeader from "../../services/auth-header";
import ChangeEmailForm from "../forms/ChangeEmailForm";

const InstructorProfile = ({ instructor }) => {
    const [timetable, setTimetable] = useState([]);
    const [day, setDay] = useState('MONDAY')
    const [showPassForm, setShowPassForm] = useState(false);
    const [showEmailForm, setShowEmailForm] = useState(false);
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
        setShowPassForm(!showPassForm)
        setShowEmailForm(false)
        setShowDailyTimetable(false)
        setShowTimetable(false)
    }
    const displayEmailForm = () => {
        setShowPassForm(false)
        setShowEmailForm(!showEmailForm)
        setShowDailyTimetable(false)
        setShowTimetable(false)
    }
    const displayDailyTimetable = () => {
        setShowPassForm(false)
        setShowEmailForm(false)
        setShowDailyTimetable(!showDailyTimetable)
        setShowTimetable(false)
    }
    const displayTimetable = () => {
        setShowPassForm(false)
        setShowEmailForm(false)
        setShowDailyTimetable(false)
        setShowTimetable(!showTimetable)
    }

    const filterTimetable = (day) => {
        return timetable
            .filter( timetable => timetable.dayOfWeek === day)
    }

    return (
        <>
            <h3 className='alt'>{instructor.honoraryStatus ? 'хоноруван ' : ''}{getInstructorTitle(instructor.title)}</h3>
            <h2 className='alt'><b>{getShortAcademicDegree(instructor.degree)} {getQualification(instructor.qualification)} {instructor.firstName} {instructor.middleName} {instructor.lastName}</b></h2>
            <h4 className='alt'><b>Член на катедра: </b>{instructor.department && instructor.department.name}</h4>
            <div className='contact-info'>
                <p className='alt'><b>Кабинет: </b>{instructor.office}</p>
                <p className='alt'><b>E-mail адрес: </b>{instructor.email}</p>
                <p className='alt'><b>Телефон за връзка: </b>{instructor.phone}</p>
            </div>
            <div className='profile-buttons'>

                <button className={showPassForm ? 'selected' : ''} onClick={() => displayPassForm()}>Смени парола</button>
                <button className={showEmailForm ? 'selected' : ''} onClick={() => displayEmailForm()}>Смени e-mail</button>
                <button className={showDailyTimetable ? 'selected' : ''} onClick={() => displayDailyTimetable()}>Дневен разпис</button>
                <button className={showTimetable ? 'selected' : ''} onClick={() => displayTimetable()}>Седмичен разпис</button>
            </div>
            {showPassForm && <ChangePassForm onSubmit={() => setShowPassForm(false)}/>}
            {showEmailForm && <ChangeEmailForm onSubmit={() => setShowEmailForm(false)}/>}
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
        </>
    )
}

export default InstructorProfile
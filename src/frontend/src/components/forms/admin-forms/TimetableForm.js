import Form from '../Form'
import {useEffect, useState} from "react";
import {getInstructorShortTitle} from "../../../util";

const TimetableForm = ({onAdd}) => {
    const [day, setDay] = useState('none')
    const [start, setStart] = useState([])
    const [end, setEnd] = useState([])
    const [room, setRoom] = useState([])
    const [program, setProgram] = useState('none')
    const [year, setYear] = useState('none')
    const [discipline, setDiscipline] = useState('none')
    const [instructor, setInstructor] = useState('none')
    const [type, setType] = useState('none')
    const [group, setGroup] = useState('none')

    const [programs, setPrograms] = useState([])
    const [disciplines, setDisciplines] = useState([])
    const [instructors, setInstructors] = useState([])

    useEffect(() => {
        const getPrograms = async () => {
            const programsFromServer = await fetchPrograms()
            setPrograms(programsFromServer)
        }

        getPrograms()
    }, [])

    const fetchPrograms = async () => {
        const res = await fetch('http://localhost:8080/programs')
        return await res.json()
    }
    const fetchProgramYearDisciplines = async (programId, year) => {
        const res = await fetch('http://localhost:8080/programs/' + programId + '/disciplines?year=' + year)
        return await res.json()
    }
    const fetchDisciplineInstructors = async (disciplineId) => {
        const res = await fetch('http://localhost:8080/disciplines/' + disciplineId + '/instructors')
        return await res.json()
    }

    const changeYear = (value) => {
        setYear(value)

        const getDisciplines = async () => {
            const disciplinesFromServer = await fetchProgramYearDisciplines(program, value)
            setDisciplines(disciplinesFromServer)
        }

        if(value !== 'none')
            getDisciplines()
    }
    const changeDiscipline = (value) => {
        setDiscipline(value)

        const getInstructors = async () => {
            const instructorsFromServer = await fetchDisciplineInstructors(value)
            setInstructors(instructorsFromServer)
        }

        if(value !== 'none')
            getInstructors()
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        // validate

        let timetable = {
            assignedInstructor: {
                id: instructor
            },
            dayOfWeek: day,
            startTime: start,
            endTime: end,
            designatedRoom: room,
            programDiscipline: {
                id: discipline
            },
            classType: type,
            studentGroup: group
        }

        onAdd(timetable)
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='form-fields'>
                <p><label>Ден от седмицата: </label>
                    <select className='alt'
                            name='days'
                            id='days'
                            onChange={event => setDay(event.target.value)}>
                        <option value='none'> - Изберете ден - </option>
                        <option value='MONDAY'>Понеделник</option>
                        <option value='TUESDAY'>Вторник</option>
                        <option value='WEDNESDAY'>Сряда</option>
                        <option value='THURSDAY'>Четвъртък</option>
                        <option value='FRIDAY'>Петък</option>
                        <option value='SATURDAY'>Събота</option>
                        <option value='SUNDAY'>Неделя</option>
                    </select>
                </p>
                <p>Начален час: <input type="text" onChange={event => setStart(event.target.value)}/></p>
                <p>Краен час: <input type="text"  onChange={event => setEnd(event.target.value)}/></p>
                <p>Кабинет: <input type="text"  onChange={event => setRoom(event.target.value)}/></p>
                <br/>
                <p><label>Специалност: </label>
                    <select className='alt'
                            name='program'
                            id='program'
                            onChange={event => setProgram(event.target.value)}>
                        <option value='none'> - Изберете специалност - </option>
                        {
                            programs.map(p => (
                                <option key={p.id} value={p.id}>{p.abbreviation}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>Курс: </label>
                    <select className='alt'
                            name='year'
                            id='year'
                            disabled={program === 'none'}
                            onChange={event => changeYear(event.target.value)}>
                        <option value='none'> - Изберете курс - </option>
                        <option value='1'>Първи</option>
                        <option value='2'>Втори</option>
                        <option value='3'>Трети</option>
                        <option value='4'>Четвърти</option>
                    </select>
                </p>
                <p><label>Дисциплина: </label>
                    <select className='alt'
                            name='discipline'
                            id='discipline'
                            disabled={year === 'none'}
                            onChange={event => changeDiscipline(event.target.value)}>
                        <option value='none'> - Изберете дисциплина - </option>
                        {
                            disciplines.map(d => (
                                <option key={d.id} value={d.id}>{d.discipline.abbreviation}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>Преподавател: </label>
                    <select className='alt'
                            name='instructor'
                            id='instructor'
                            disabled={discipline === 'none'}
                            onChange={event => setInstructor(event.target.value)}>
                        <option value='none'> - Изберете преподавател - </option>
                        {
                            instructors.map(i => (
                                <option key={i.id} value={i.id}>{getInstructorShortTitle(i.title)} {i.lastName}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>Вид занятие: </label>
                    <select className='alt'
                            name='type'
                            id='type'
                            onChange={event => setType(event.target.value)}>
                        <option value='none'> - Изберете занятие - </option>
                        <option value='LECTURE'>Лекция</option>
                        <option value='LAB_TUTORIAL'>Лабораторно упражнение</option>
                        <option value='SEMINAR'>Семинарно упражнение</option>
                        <option value='EDU_PRACTICE'>Учебна практика</option>
                    </select>
                </p>
                <p><label>Група: </label>
                    <select className='alt'
                            name='group'
                            id='group'
                            disabled={type === 'LECTURE'}
                            onChange={event => setGroup(event.target.value)}>
                        <option value='none'> - Изберете група - </option>
                        <option value='1'>1-ва</option>
                        <option value='2'>2-ра</option>
                        <option value='3'>3-та</option>
                        <option value='4'>4-та</option>
                        <option value='5'>5-та</option>
                        <option value='6'>6-та</option>
                        <option value='7'>7-ма</option>
                        <option value='8'>8-ма</option>
                        <option value='9'>9-та</option>
                        <option value='10'>10-та</option>
                    </select>
                </p>
            </div>
            <input type="submit" value="ДОБАВИ"/>
        </Form>
    )
}

export default TimetableForm;
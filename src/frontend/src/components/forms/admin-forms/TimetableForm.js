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
                <p><label>?????? ???? ??????????????????: </label>
                    <select className='alt'
                            name='days'
                            id='days'
                            onChange={event => setDay(event.target.value)}>
                        <option value='none'> - ???????????????? ?????? - </option>
                        <option value='MONDAY'>????????????????????</option>
                        <option value='TUESDAY'>??????????????</option>
                        <option value='WEDNESDAY'>??????????</option>
                        <option value='THURSDAY'>??????????????????</option>
                        <option value='FRIDAY'>??????????</option>
                        <option value='SATURDAY'>????????????</option>
                        <option value='SUNDAY'>????????????</option>
                    </select>
                </p>
                <p>?????????????? ??????: <input type="text" onChange={event => setStart(event.target.value)}/></p>
                <p>?????????? ??????: <input type="text"  onChange={event => setEnd(event.target.value)}/></p>
                <p>??????????????: <input type="text"  onChange={event => setRoom(event.target.value)}/></p>
                <br/>
                <p><label>??????????????????????: </label>
                    <select className='alt'
                            name='program'
                            id='program'
                            onChange={event => setProgram(event.target.value)}>
                        <option value='none'> - ???????????????? ?????????????????????? - </option>
                        {
                            programs.map(p => (
                                <option key={p.id} value={p.id}>{p.abbreviation}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>????????: </label>
                    <select className='alt'
                            name='year'
                            id='year'
                            disabled={program === 'none'}
                            onChange={event => changeYear(event.target.value)}>
                        <option value='none'> - ???????????????? ???????? - </option>
                        <option value='1'>??????????</option>
                        <option value='2'>??????????</option>
                        <option value='3'>??????????</option>
                        <option value='4'>????????????????</option>
                    </select>
                </p>
                <p><label>????????????????????: </label>
                    <select className='alt'
                            name='discipline'
                            id='discipline'
                            disabled={year === 'none'}
                            onChange={event => changeDiscipline(event.target.value)}>
                        <option value='none'> - ???????????????? ???????????????????? - </option>
                        {
                            disciplines.map(d => (
                                <option key={d.id} value={d.id}>{d.discipline.abbreviation}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>????????????????????????: </label>
                    <select className='alt'
                            name='instructor'
                            id='instructor'
                            disabled={discipline === 'none'}
                            onChange={event => setInstructor(event.target.value)}>
                        <option value='none'> - ???????????????? ???????????????????????? - </option>
                        {
                            instructors.map(i => (
                                <option key={i.id} value={i.id}>{getInstructorShortTitle(i.title)} {i.lastName}</option>
                            ))
                        }
                    </select>
                </p>
                <p><label>?????? ??????????????: </label>
                    <select className='alt'
                            name='type'
                            id='type'
                            onChange={event => setType(event.target.value)}>
                        <option value='none'> - ???????????????? ?????????????? - </option>
                        <option value='LECTURE'>????????????</option>
                        <option value='LAB_TUTORIAL'>?????????????????????? ????????????????????</option>
                        <option value='SEMINAR'>?????????????????? ????????????????????</option>
                        <option value='EDU_PRACTICE'>???????????? ????????????????</option>
                    </select>
                </p>
                <p><label>??????????: </label>
                    <select className='alt'
                            name='group'
                            id='group'
                            disabled={type === 'LECTURE'}
                            onChange={event => setGroup(event.target.value)}>
                        <option value='none'> - ???????????????? ?????????? - </option>
                        <option value='1'>1-????</option>
                        <option value='2'>2-????</option>
                        <option value='3'>3-????</option>
                        <option value='4'>4-????</option>
                        <option value='5'>5-????</option>
                        <option value='6'>6-????</option>
                        <option value='7'>7-????</option>
                        <option value='8'>8-????</option>
                        <option value='9'>9-????</option>
                        <option value='10'>10-????</option>
                    </select>
                </p>
            </div>
            <input type="submit" value="????????????"/>
        </Form>
    )
}

export default TimetableForm;
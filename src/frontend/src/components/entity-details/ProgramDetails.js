import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import DisciplineCard from '../cards/DisciplineCard'
import { getAcademicDegree } from '../../util.js'
import FilterForm from "../forms/FilterForm";
import StudentCard from "../cards/StudentCard";

const ProgramDetails = () => {
    let { id } = useParams();
    const [program, setProgram] = useState([])
    const [pds, setPds] = useState([])
    const [students, setStudents] = useState([])
    const [yearD, setYearD] = useState('none');
    const [yearS, setYearS] = useState('none');
    const [group, setGroup] = useState('none');

    useEffect(() => {
        const getProgram = async () => {
            const programFromServer = await fetchProgram(id)
            setProgram(programFromServer)
        }

        const getPds = async () => {
            const pdsFromServer = await fetchPds(id)
            setPds(pdsFromServer)
        }

        const getProgramStudents = async () => {
            const studentsFromServer = await fetchProgramStudents(id)
            setStudents(studentsFromServer)
        }

        getProgram()
        getPds()
        getProgramStudents()
    }, [])

    const fetchProgram = async (id) => {
        const res = await fetch('http://localhost:8080/programs/' + id)
        const data = await res.json()
        
        return data
    }
    const fetchPds = async (id) => {
        const res = await fetch('http://localhost:8080/programs/' + id + '/disciplines')
        const data = await res.json()
        
        return data
    }
    const fetchProgramStudents = async (id) => {
        const res = await fetch('http://localhost:8080/programs/' + id + '/students')
        const data = await res.json()

        return data
    }    

    return (
        <>
            <h2 className='alt'>{program.name}</h2>
            <h4 className='alt'><b>Абревиатура: </b>{program.abbreviation}</h4>
            <h4 className='alt'><b>Обучаваща катедра: </b>{program.department && program.department.name}</h4>
            <h4 className='alt'><b>Поток: </b>{program.academicStream}</h4>
            <h4 className='alt'><b>Образователно-квалификационна степен: </b>{getAcademicDegree(program.degree)}</h4>
            <h4 className='alt'><b>Срок на обучение: </b>{program.educationPeriod} години / {program.educationPeriod * 2} семестъра</h4>
            {pds.length > 0 && (
                <FilterForm>
                    <h3>Преподавани дисциплини</h3>
                    <select name='year-d' id='year-d' onChange={event => {setYearD(event.target.value)}}>
                        <option value='none'> - Изберете курс - </option>
                        <option value='1'>Първи</option>
                        <option value='2'>Втори</option>
                        <option value='3'>Трети</option>
                        <option value='4'>Четвърти</option>
                    </select>
                </FilterForm>
            )}
            {
                pds.filter(pd => pd.academicYear == yearD).map(pd => (
                    <DisciplineCard
                        key={pd.discipline.id}
                        discipline={pd.discipline}
                        year={pd.academicYear}
                    />
                ))
            }
            {students.length > 0 && (
                <FilterForm>
                    <h3>Записани студенти</h3>
                    <select name='year-s' id='year-s' onChange={event => {setYearS(event.target.value)}}>
                        <option value='none'> - Изберете курс - </option>
                        <option value='1'>Първи</option>
                        <option value='2'>Втори</option>
                        <option value='3'>Трети</option>
                        <option value='4'>Четвърти</option>
                    </select>
                    <select name='group' id='group' onChange={event => {setGroup(event.target.value)}}>
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
                </FilterForm>
            )}
            {
                students.filter(s => s.academicYear == yearS && s.studentGroup == group).map(s => (
                    <StudentCard
                        key={s.id}
                        student={s}
                    />
                ))
            }
        </>
    )
}

export default ProgramDetails

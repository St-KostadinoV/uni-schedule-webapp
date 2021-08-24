import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import DisciplineCard from '../cards/DisciplineCard'
import { getAcademicDegree } from '../../util.js'

const ProgramDetails = () => {
    let { id } = useParams();
    const [program, setProgram] = useState([])
    const [pds, setPds] = useState([])
    const [students, setStudents] = useState([])

    useEffect(() => {
        const getProgram = async () => {
            const programFromServer = await fetchProgram(id)
            setProgram(programFromServer)
        }

        const getPds = async () => {
            const pdsFromServer = await fetchPds(id)
            setPds(pdsFromServer)
        }

        getProgram()
        getPds()
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
        <div className='centered-content'>
            <h2 className='alt'>{program.name}</h2>
            <h4 className='alt'><b>Абревиатура: </b>{program.abbreviation}</h4>
            <h4 className='alt'><b>Обучаваща катедра: </b>{program.department && program.department.name}</h4>
            <h4 className='alt'><b>Поток: </b>{program.academicStream}</h4>
            <h4 className='alt'><b>Образователно-квалификационна степен: </b>{getAcademicDegree(program.degree)}</h4>
            <h4 className='alt'><b>Срок на обучение: </b>{program.educationPeriod} години / {program.educationPeriod * 2} семестъра</h4>
            {pds.length > 0 && <h3 className='alt'>Водени дисциплини</h3>}
            {
                pds.map( pd => (
                    <DisciplineCard
                        key={pd.discipline.id}
                        discipline={pd.discipline}
                        year={pd.academicYear}
                    />
                ))
            }
        </div>
    )
}

export default ProgramDetails

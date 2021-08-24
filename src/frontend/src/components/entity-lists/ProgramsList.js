import { useState, useEffect } from 'react'
import ProgramCard from '../cards/ProgramCard'

const ProgramsList = () => {
    const [programs, setPrograms] = useState([])

    useEffect(() => {
        const getPrograms = async () => {
          const programsFromServer = await fetchPrograms()
          setPrograms(programsFromServer)
        }
    
        getPrograms()
    }, [])

    const fetchPrograms = async () => {
        const res = await fetch('http://localhost:8080/programs')
        const data = await res.json()
    
        return data
    }

    return (
        <div className="centered-content">
            <h2 className='alt'>Специалности</h2>
            {
                programs.map( program => (
                    <ProgramCard
                        key={program.id}
                        program={program}
                    />
                ))
            }
        </div>
    )
}

export default ProgramsList

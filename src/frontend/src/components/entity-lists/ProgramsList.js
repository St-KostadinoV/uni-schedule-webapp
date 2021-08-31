import { useState, useEffect } from 'react'
import ProgramCard from '../cards/ProgramCard'
import FilterForm from "../forms/FilterForm";

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
        <>
            <FilterForm><h2><b>Специалности</b></h2></FilterForm>
            {
                programs.map( program => (
                    <ProgramCard
                        key={program.id}
                        program={program}
                    />
                ))
            }
        </>
    )
}

export default ProgramsList

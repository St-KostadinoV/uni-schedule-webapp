import { useState, useEffect } from 'react'
import DisciplineCard from '../cards/DisciplineCard'
import FilterForm from "../forms/FilterForm";

const DisciplinesList = () => {
    const [disciplines, setDisciplines] = useState([])

    useEffect(() => {
        const getDisciplines = async () => {
          const disciplinesFromServer = await fetchDisciplines()
          setDisciplines(disciplinesFromServer)
        }
    
        getDisciplines()
    }, [])

    const fetchDisciplines = async () => {
        const res = await fetch('http://localhost:8080/disciplines')
        const data = await res.json()
    
        return data
    }

    return (
        <>
            <FilterForm><h2><b>Дисциплини</b></h2></FilterForm>
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

export default DisciplinesList

import { useState, useEffect } from 'react'
import DisciplineCard from '../cards/DisciplineCard'

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
        <div className="centered-content">
            <h2 className='alt'>Дисциплини</h2>
            {
                disciplines.map( discipline => (
                    <DisciplineCard
                        key={discipline.id}
                        discipline={discipline}
                    />
                ))
            }
        </div>
    )
}

export default DisciplinesList

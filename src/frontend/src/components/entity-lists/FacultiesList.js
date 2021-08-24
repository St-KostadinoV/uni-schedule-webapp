import { useState, useEffect } from 'react'
import FacultyCard from "../cards/FacultyCard"

const FacultiesList = () => {
    const [faculties, setFaculties] = useState([])

    useEffect(() => {
        const getFaculties = async () => {
          const facultiesFromServer = await fetchFaculties()
          setFaculties(facultiesFromServer)
        }
    
        getFaculties()
    }, [])
    
    const fetchFaculties = async () => {
        const res = await fetch('http://localhost:8080/faculties')
        const data = await res.json()
    
        return data
    }

    return (
        <div className="centered-content">
            <h2 className='alt'>Факултети и Колежи в ТУ-Варна</h2>
            {
                faculties.map( fac => (
                    <FacultyCard
                        key={fac.id}
                        faculty={fac}
                    />
                ))
            }
        </div>
    )
}

export default FacultiesList
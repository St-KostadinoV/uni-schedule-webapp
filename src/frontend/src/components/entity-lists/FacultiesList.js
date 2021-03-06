import { useState, useEffect } from 'react'
import FacultyCard from "../cards/FacultyCard"
import FilterForm from "../forms/FilterForm";

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
        <>
            <FilterForm><h2><b>Факултети и Колежи в ТУ-Варна</b></h2></FilterForm>
            {
                faculties.map( fac => (
                    <FacultyCard
                        key={fac.id}
                        faculty={fac}
                    />
                ))
            }
        </>
    )
}

export default FacultiesList
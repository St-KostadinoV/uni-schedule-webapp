import { useState, useEffect } from 'react'
import FacultyCard from "./FacultyCard"
import authHeader from '../../../services/auth-header';

const FacultyCardList = () => {
    const [faculties, setFaculties] = useState([])

    useEffect(() => {
        const getFaculties = async () => {
            const facultiesFromServer = await fetchFaculties()
            setFaculties(facultiesFromServer)
        }

        getFaculties()
    }, [])

    const fetchFaculties = async () => {
        const res = await fetch('http://localhost:8080/admin/faculty', {headers: authHeader()})
        const data = await res.json()

        return data
    }

    return (
        <>
            <h2 className='alt'>Факултети</h2>
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

export default FacultyCardList
import { useState, useEffect } from 'react'
import FacultyCard from "./FacultyCard"
import authHeader from '../../../services/auth-header';
import FilterForm from "../../forms/FilterForm";
import FacultyForm from "../../forms/admin-forms/FacultyForm";

const FacultyCardList = () => {
    const [faculties, setFaculties] = useState([])
    const [showAdd, setShowAdd] = useState(false)

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

    const addFaculty = async (faculty) => {
        const res = await fetch('http://localhost:8080/admin/faculty', {
            method: 'POST',
            headers: {
                ...authHeader(),
                'Content-type': 'application/json'
            },
            body: JSON.stringify(faculty)
        })

        const data = await res.json()
        setFaculties([...faculties, data])
        setShowAdd(false)
    }

    const deleteFaculty = async (id) => {
        await fetch('http://localhost:8080/admin/faculty/' + id, {
            method: 'DELETE',
            headers: authHeader()
        })

        setFaculties(faculties.filter(f => f.id !== id))
    }

    return (
        <>
            <FilterForm>
                <h2><b>Факултети</b></h2>
                <button className='add' onClick={() => setShowAdd(!showAdd)}>Добави</button>
            </FilterForm>
            {showAdd && <FacultyForm onAdd={addFaculty}/>}
            {
                faculties.length > 0 && faculties.map( fac => (
                    <FacultyCard
                        key={fac.id}
                        faculty={fac}
                        onDelete={deleteFaculty}
                    />
                ))
            }
        </>
    )
}

export default FacultyCardList
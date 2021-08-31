import { useState, useEffect } from 'react'
import InstructorCard from '../cards/InstructorCard'
import FilterForm from "../forms/FilterForm";

const InstructorsList = () => {
    const [instructors, setInstructors] = useState([])

    useEffect(() => {
        const getInstructors = async () => {
          const instructorsFromServer = await fetchInstructors()
          setInstructors(instructorsFromServer)
        }
    
        getInstructors()
    }, [])

    const fetchInstructors = async () => {
        const res = await fetch('http://localhost:8080/instructors')
        const data = await res.json()
    
        return data
    }

    return (
        <>
            <FilterForm><h2><b>Преподаватели</b></h2></FilterForm>
            {
                instructors.map( instructor => (
                    <InstructorCard
                        key={instructor.id}
                        instructor={instructor}
                    />
                ))
            }
        </>
    )
}

export default InstructorsList

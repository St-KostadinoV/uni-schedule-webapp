import { useState, useEffect } from 'react'
import InstructorCard from '../cards/InstructorCard'

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
        <div className="centered-content">
            <h2 className='alt'>Преподаватели</h2>
            {
                instructors.map( instructor => (
                    <InstructorCard
                        key={instructor.id}
                        instructor={instructor}
                    />
                ))
            }
        </div>
    )
}

export default InstructorsList

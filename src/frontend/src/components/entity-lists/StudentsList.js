import { useState, useEffect } from 'react'
import StudentCard from '../cards/StudentCard'
import FilterForm from "../forms/FilterForm";

const StudentsList = () => {
    const [students, setStudents] = useState([])

    useEffect(() => {
        const getStudents = async () => {
          const studentsFromServer = await fetchStudents()
          setStudents(studentsFromServer)
        }
    
        getStudents()
    }, [])

    const fetchStudents = async () => {
        const res = await fetch('http://localhost:8080/students')
        const data = await res.json()
    
        return data
    }

    return (
        <>
            <FilterForm><h2><b>Студенти</b></h2></FilterForm>
            {
                students.map( student => (
                    <StudentCard
                        key={student.id}
                        student={student}
                    />
                ))
            }
        </>
    )
}

export default StudentsList

import { useState, useEffect } from 'react'
import StudentCard from '../cards/StudentCard'

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
        <div className="centered-content">
            <h2 className='alt'>Студенти</h2>
            {
                students.map( student => (
                    <StudentCard
                        key={student.id}
                        student={student}
                    />
                ))
            }
        </div>
    )
}

export default StudentsList

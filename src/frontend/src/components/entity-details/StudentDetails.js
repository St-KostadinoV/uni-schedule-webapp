import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

const StudentDetails = () => {
    let { id } = useParams();
    const [student, setStudent] = useState([])

    useEffect(() => {
        const getStudent = async () => {
            const studentFromServer = await fetchStudent(id)
            setStudent(studentFromServer)
        }

        getStudent()
    }, [])

    const fetchStudent = async (id) => {
        const res = await fetch('http://localhost:8080/students/' + id)
        const data = await res.json()
        
        return data
    }

    return (
        <div className='centered-content'>
            <h2 className='alt'>{student.firstName} {student.middleName} {student.lastName}</h2>
            <h4 className='alt'><b>Факултетен номер: </b>{student.facultyNumber}</h4>
            <h4 className='alt'><b>Специалност: </b>{student.academicProgram && student.academicProgram.name}</h4>
            <h4 className='alt'><b>Курс: </b>{student.academicYear}&nbsp;&nbsp;&nbsp;&nbsp;<b>Група: </b>{student.studentGroup}</h4>
            <h4 className='alt'><b>E-mail адрес:</b>{student.email}</h4>
            <h4 className='alt'><b>Телефон за връзка: </b>{student.phone}</h4>
        </div>
    )
}

export default StudentDetails

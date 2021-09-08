import { useState, useEffect } from 'react'
import StudentCard from '../cards/StudentCard'
import FilterForm from "../forms/FilterForm";

const StudentsList = () => {
    const [students, setStudents] = useState([])
    const [fname, setFname] = useState("")
    const [fnum, setFnum] = useState("")
    const [lname, setLname] = useState("")

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

    const filterStudents = (student) => {
        return (!fnum || student.facultyNumber.includes(fnum))
            && (!fname || student.firstName.toLowerCase().includes(fname.toLowerCase()))
            && (!lname || student.lastName.toLowerCase().includes(lname.toLowerCase()))
    }

    return (
        <>
            <FilterForm>
                <h2><b>Студенти</b></h2>
            </FilterForm>
            <FilterForm style={{"display": "flex", "marginTop": "16px"}}>
                <input type='text' className='alt' value={fnum} placeholder='Факултетен номер' onChange={event => setFnum(event.target.value)}/>
                <input type='text' className='alt' value={fname} placeholder='Име' onChange={event => setFname(event.target.value)}/>
                <input type='text' className='alt' value={lname} placeholder='Фамилия' onChange={event => setLname(event.target.value)}/>
            </FilterForm>
            {
                students
                    .filter(filterStudents)
                    .map( student => (
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

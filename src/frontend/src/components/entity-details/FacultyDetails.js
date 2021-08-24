import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import DepartmentCard from '../cards/DepartmentCard';

const FacultyDetails = () => {
    let { id } = useParams();
    const [faculty, setFaculty] = useState([])
    const [depts, setDepts] = useState([])

    useEffect(() => {
        const getFaculty = async () => {
          const facultyFromServer = await fetchFaculty(id)
          setFaculty(facultyFromServer)
        }

        const getFacultyDepts = async () => {
            const deptsFromServer = await fetchFacultyDepts(id)
            setDepts(deptsFromServer)
          }
    
        getFaculty()
        getFacultyDepts()
    }, [])
    
    const fetchFaculty = async (id) => {
        const res = await fetch('http://localhost:8080/faculties/' + id)
        const data = await res.json()
        return data
    }

    const fetchFacultyDepts = async (id) => {
        const res = await fetch('http://localhost:8080/faculties/' + id + '/departments')
        const data = await res.json()
        return data
    }

    return (
        <div className="centered-content">
            <h2 className='alt'>{faculty.name}</h2>
            <h4 className='alt'><b>Абревиатура: </b>{faculty.abbreviation}</h4>
            {depts.length > 0 && <h3 className='alt'>Принадлежащи катедри</h3>}
            {
                depts.map( dept => (
                    <DepartmentCard
                        key={dept.id}
                        dept={dept}
                    />
                ))
            }
        </div>
    )
}

export default FacultyDetails

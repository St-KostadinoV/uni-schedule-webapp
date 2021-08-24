import { useState, useEffect } from 'react'
import DepartmentCard from '../cards/DepartmentCard'

const DepartmentsList = () => {
    const [depts, setDepts] = useState([])

    useEffect(() => {
        const getDepts = async () => {
          const deptsFromServer = await fetchDepts()
          setDepts(deptsFromServer)
        }
    
        getDepts()
    }, [])

    const fetchDepts = async () => {
        const res = await fetch('http://localhost:8080/departments')
        const data = await res.json()
    
        return data
    }

    return (
        <div className="centered-content">
            <h2 className='alt'>Катедри</h2>
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

export default DepartmentsList

import { useState, useEffect } from 'react'
import DepartmentCard from '../cards/DepartmentCard'
import FilterForm from "../forms/FilterForm";

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
        <>
            <FilterForm><h2><b>Катедри</b></h2></FilterForm>
            {
                depts.map( dept => (
                    <DepartmentCard
                        key={dept.id}
                        dept={dept}
                    />
                ))
            }
        </>
    )
}

export default DepartmentsList

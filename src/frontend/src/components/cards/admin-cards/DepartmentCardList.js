import { useState, useEffect } from 'react'
import authHeader from '../../../services/auth-header';
import DepartmentCard from "./DepartmentCard";
import FilterForm from "../../forms/FilterForm";
import DepartmentForm from "../../forms/admin-forms/DepartmentForm";

const DepartmentCardList = () => {
    const [departments, setDepartments] = useState([])
    const [showAdd, setShowAdd] = useState(false)

    useEffect(() => {
        const getDepartments = async () => {
            const departmentsFromServer = await fetchDepartments()
            setDepartments(departmentsFromServer)
        }

        getDepartments()
    }, [])

    const fetchDepartments = async () => {
        const res = await fetch('http://localhost:8080/admin/department', {headers: authHeader()})
        const data = await res.json()

        return data
    }

    const addDepartment = async (department) => {
        const res = await fetch('http://localhost:8080/admin/department', {
            method: 'POST',
            headers: {
                ...authHeader(),
                'Content-type': 'application/json'
            },
            body: JSON.stringify(department)
        })

        const data = await res.json()
        setDepartments([...departments, data])
        setShowAdd(false)
    }

    const deleteDepartment = async (id) => {
        await fetch('http://localhost:8080/admin/department/' + id, {
            method: 'DELETE',
            headers: authHeader()
        })

        setDepartments(departments.filter(d => d.id !== id))
    }

    return (
        <>
            <FilterForm>
                <h2><b>Катедри</b></h2>
                <button className='add' onClick={() => setShowAdd(!showAdd)}>Добави</button>
            </FilterForm>
            {showAdd && <DepartmentForm onAdd={addDepartment}/>}
            {
                departments.map( d => (
                    <DepartmentCard
                        key={d.id}
                        department={d}
                        onDelete={deleteDepartment}
                    />
                ))
            }
        </>
    )
}

export default DepartmentCardList
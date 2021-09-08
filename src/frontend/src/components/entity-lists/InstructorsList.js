import { useState, useEffect } from 'react'
import InstructorCard from '../cards/InstructorCard'
import FilterForm from "../forms/FilterForm";

const InstructorsList = () => {
    const [instructors, setInstructors] = useState([])
    const [departments, setDepartments] = useState([])
    const [department, setDepartment] = useState("")
    const [fname, setFname] = useState("")
    const [mname, setMname] = useState("")
    const [lname, setLname] = useState("")

    useEffect(() => {
        const getInstructors = async () => {
          const instructorsFromServer = await fetchInstructors()
          setInstructors(instructorsFromServer)
        }
        const getDepartments = async () => {
            const departmentsFromServer = await fetchDepartments()
            setDepartments(departmentsFromServer)
        }
    
        getInstructors()
        getDepartments()
    }, [])

    const fetchInstructors = async () => {
        const res = await fetch('http://localhost:8080/instructors')
        const data = await res.json()
    
        return data
    }

    const fetchDepartments = async () => {
        const res = await fetch('http://localhost:8080/departments')
        const data = await res.json()

        return data
    }

    const filterInstructors = (instructor) => {
        return (!department || instructor.department.abbreviation === department)
                &&(!fname || instructor.firstName.toLowerCase().includes(fname.toLowerCase()))
                && (!mname || instructor.middleName.toLowerCase().includes(mname.toLowerCase()))
                && (!lname || instructor.lastName.toLowerCase().includes(lname.toLowerCase()))
    }

    return (
        <>
            <FilterForm>
                <h2><b>Преподаватели</b></h2>
                <select style={{"width": "280px"}} onChange={event => setDepartment(event.target.value)}>
                    <option value=''> - Изберете катедра - </option>
                    {departments.map( department => (
                        <option key={department.id} value={department.abbreviation}>{department.name}</option>
                    ))}
                </select>
            </FilterForm>
            <FilterForm style={{"display": "flex", "marginTop": "16px"}}>
                <input type='text' className='alt' value={fname} placeholder='Име' onChange={event => setFname(event.target.value)}/>
                <input type='text' className='alt' value={mname} placeholder='Презиме' onChange={event => setMname(event.target.value)}/>
                <input type='text' className='alt' value={lname} placeholder='Фамилия' onChange={event => setLname(event.target.value)}/>
            </FilterForm>
            {
                instructors
                    .filter(filterInstructors)
                    .map( instructor => (
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

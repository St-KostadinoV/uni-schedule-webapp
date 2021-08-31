import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import InstructorCard from '../cards/InstructorCard';
import ProgramCard from '../cards/ProgramCard';
import DisciplineCard from '../cards/DisciplineCard';
import FilterForm from "../forms/FilterForm";

const DepartmentDetails = () => {
    let { id } = useParams();
    const [dept, setDept] = useState([])
    const [instructors, setInstructors] = useState([])
    const [programs, setPrograms] = useState([])
    const [disciplines, setDisciplines] = useState([])

    useEffect(() => {
        const getDept = async () => {
            const deptFromServer = await fetchDept(id)
            setDept(deptFromServer)
        }

        const getDeptInstructors = async () => {
            const instructorsFromServer = await fetchDeptInstructors(id)
            setInstructors(instructorsFromServer)
        }

        const getDeptPrograms = async () => {
            const programsFromServer = await fetchDeptPrograms(id)
            setPrograms(programsFromServer)
        }

        const getDeptDisciplines = async () => {
            const disciplinesFromServer = await fetchDeptDisciplines(id)
            setDisciplines(disciplinesFromServer)
        }

        getDept()
        getDeptInstructors()
        getDeptPrograms()
        getDeptDisciplines()
    }, [])

    const fetchDept = async (id) => {
        const res = await fetch('http://localhost:8080/departments/' + id)
        const data = await res.json()
        
        return data
    }

    const fetchDeptInstructors = async (id) => {
        const res = await fetch('http://localhost:8080/departments/' + id + '/instructors')
        const data = await res.json()

        return data
    }

    const fetchDeptPrograms = async (id) => {
        const res = await fetch('http://localhost:8080/departments/' + id + '/programs')
        const data = await res.json()

        return data
    }

    const fetchDeptDisciplines = async (id) => {
        const res = await fetch('http://localhost:8080/departments/' + id + '/disciplines')
        const data = await res.json()
        
        return data
    }

    return (
        <>
            <h2 className='alt'>{dept.name}</h2>
            <h4 className='alt'><b>Абревиатура: </b>{dept.abbreviation}</h4>
            <h4 className='alt'><b>Част от: </b>{dept.faculty && dept.faculty.name}</h4>
            {instructors.length > 0 && <FilterForm><h3>Академичен състав</h3></FilterForm>}
            {
                instructors.map( instructor => (
                    <InstructorCard
                        key={instructor.id}
                        instructor={instructor}
                    />
                ))
            }
            {programs.length > 0 && <FilterForm><h3>Водени специалности</h3></FilterForm>}
            {
                programs.map( program => (
                    <ProgramCard
                        key={program.id}
                        program={program}
                    />
                ))
            }
            {disciplines.length > 0 && <FilterForm><h3>Водени дисциплини</h3></FilterForm>}
            {
                disciplines.map( discipline => (
                    <DisciplineCard
                        key={discipline.id}
                        discipline={discipline}
                    />
                ))
            }
        </>
    )
}

export default DepartmentDetails

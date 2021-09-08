import {useState} from "react";
import FacultyCardList from "../cards/admin-cards/FacultyCardList";
import DepartmentCardList from "../cards/admin-cards/DepartmentCardList";

const FrontDeskProfile  = () => {
    const [showFaculties, setShowFaculties] = useState(false);
    const [showDepartments, setShowDepartments] = useState(false);
    const [showPrograms, setShowPrograms] = useState(false);
    const [showInstructors, setShowInstructors] = useState(false);
    const [showDisciplines, setShowDisciplines] = useState(false);
    const [showStudents, setShowStudents] = useState(false);

    const displayFaculties = () => {
        setShowFaculties(!showFaculties)
        setShowDepartments(false)
        setShowPrograms(false)
        setShowInstructors(false)
        setShowDisciplines(false)
        setShowStudents(false)
    }
    const displayDepartments = () => {
        setShowFaculties(false)
        setShowDepartments(!showDepartments)
        setShowPrograms(false)
        setShowInstructors(false)
        setShowDisciplines(false)
        setShowStudents(false)
    }
    const displayPrograms = () => {
        setShowFaculties(false)
        setShowDepartments(false)
        setShowPrograms(!showPrograms)
        setShowInstructors(false)
        setShowDisciplines(false)
        setShowStudents(false)
    }
    const displayInstructors = () => {
        setShowFaculties(false)
        setShowDepartments(false)
        setShowPrograms(false)
        setShowInstructors(!showInstructors)
        setShowDisciplines(false)
        setShowStudents(false)
    }
    const displayDisciplines = () => {
        setShowFaculties(false)
        setShowDepartments(false)
        setShowPrograms(false)
        setShowInstructors(false)
        setShowDisciplines(!showDisciplines)
        setShowStudents(false)
    }
    const displayStudents = () => {
        setShowFaculties(false)
        setShowDepartments(false)
        setShowPrograms(false)
        setShowInstructors(false)
        setShowDisciplines(false)
        setShowStudents(!showStudents)
    }

    return (
        <>
            <h2 className='alt'><b>Фронт офис</b></h2>
            <div className='profile-buttons' style={{marginBottom: 0}}>
                <button onClick={() => displayFaculties()}>Факултети</button>
                <button onClick={() => displayDepartments()}>Катедри</button>
                <button onClick={() => displayPrograms()}>Специалности</button>
            </div>
            <div className='profile-buttons' style={{marginTop: 0}}>
                <button style={{marginTop: 0}} onClick={() => displayInstructors()}>Преподаватели</button>
                <button style={{marginTop: 0}} onClick={() => displayDisciplines()}>Дисциплини</button>
                <button style={{marginTop: 0}} onClick={() => displayStudents()}>Студенти</button>
            </div>
            {showFaculties && <FacultyCardList/>}
            {showDepartments && <DepartmentCardList/>}
        </>
    )
}

export default FrontDeskProfile
import FilterForm from "../forms/FilterForm";
import {useEffect, useState} from "react";
import TimetableForm from "../forms/admin-forms/TimetableForm";
import TimetableCardList from "../cards/admin-cards/TimetableCardList";
import authHeader from "../../services/auth-header";

const EduDeptProfile = () => {
    const [showForm, setShowForm] = useState(false);
    const [showEditForm, setShowEditForm] = useState(false);
    const [timetables, setTimetables] = useState([])

    useEffect(() => {
        const getTimetables = async () => {
            const timetablesFromServer = await fetchTimetables()
            setTimetables(timetablesFromServer)
        }

        getTimetables()
    }, [])

    const fetchTimetables = async () => {
        const res = await fetch('http://localhost:8080/admin/timetable', {headers: authHeader()})
        const data = await res.json()

        return data
    }

    const addTimetable = async (timetable) => {
        console.log(timetable)
        const res = await fetch('http://localhost:8080/admin/timetable', {
            method: 'POST',
            headers: {
                ...authHeader(),
                'Content-type': 'application/json'
            },
            body: JSON.stringify(timetable)
        })

        const data = await res.json()

        setTimetables([...timetables, data])
    }

    const editTimetable = async (id) => {

    }

    const deleteTimetable = async (id) => {
        alert('You tried to delete timetable with id=' + id)
    }

    const editClicked = (id) => {
        alert('You tried to edit timetable with id=' + id)
    }

    return (
        <>
            <h2 className='alt'><b>Учебен отдел</b></h2>
            <FilterForm>
                <h2><b>Разписи</b></h2>
                <button className='add' onClick={() => setShowForm(!showForm)}>Добави</button>
            </FilterForm>
            {showForm && <TimetableForm onAdd={addTimetable}/>}
            {showEditForm && <TimetableForm/>}
            <TimetableCardList timetables={timetables} onDelete={deleteTimetable} onEdit={editClicked}/>
        </>
    )
}

export default EduDeptProfile
import { useState, useEffect } from 'react'
import TimetableCard from "./TimetableCard"
import authHeader from '../../../services/auth-header';

const TimetableCardList = () => {
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

    return (
        <>
            {timetables.map(t => (
                <TimetableCard
                    key={t.id}
                    timetable={t}
                />
            ))}
        </>
    )
}

export default TimetableCardList
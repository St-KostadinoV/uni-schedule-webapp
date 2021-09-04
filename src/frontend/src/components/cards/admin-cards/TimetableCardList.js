import TimetableCard from "./TimetableCard"

const TimetableCardList = ({ timetables, onDelete, onEdit }) => {
    return (
        <>
            {timetables.map(t => (
                <TimetableCard
                    key={t.id}
                    timetable={t}
                    onDelete={onDelete}
                    onEdit={onEdit}
                />
            ))}
        </>
    )
}

export default TimetableCardList
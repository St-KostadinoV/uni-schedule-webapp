import EditCard from "./EditCard";

const FacultyCard = ({ faculty, onDelete }) => {
    return (
        <EditCard itemId={faculty.id} onDelete={onDelete}>
            <h4>{faculty.name}</h4>
            <p style={{marginTop: '8px'}}><b>Абревиатура: </b>{faculty.abbreviation}</p>
        </EditCard>
    )
}

export default FacultyCard
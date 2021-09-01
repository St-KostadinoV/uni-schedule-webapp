import EditCard from "./EditCard";

const FacultyCard = ({ faculty }) => {
    return (
        <EditCard>
            <h4>{faculty.name}</h4>
            <p style={{marginTop: '8px'}}><b>Абревиатура: </b>{faculty.abbreviation}</p>
        </EditCard>
    )
}

export default FacultyCard
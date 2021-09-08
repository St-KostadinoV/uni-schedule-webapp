import EditCard from "./EditCard";

const DepartmentCard = ({ department, onDelete }) => {
    return (
        <EditCard itemId={department.id} onDelete={onDelete}>
            <h4>{department.name}</h4>
            <p style={{marginTop: '8px'}}><b>Абревиатура: </b>{department.abbreviation}</p>
            <p style={{marginTop: '8px'}}><b>Факултет: </b>{department.faculty.abbreviation}</p>
        </EditCard>
    )
}

export default DepartmentCard;
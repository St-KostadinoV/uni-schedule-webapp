import Card from '../Card'

const FacultyCard = ({ faculty }) => {
    return (
        <Card>
            <h4>{faculty.name}</h4>
            <p style={{marginTop: '8px'}}><b>Абревиатура: </b>{faculty.abbreviation}</p>
        </Card>
    )
}

export default FacultyCard
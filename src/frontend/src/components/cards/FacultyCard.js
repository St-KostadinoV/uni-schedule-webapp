import { Link } from 'react-router-dom'
import Card from './Card'

const FacultyCard = ({ faculty }) => {
    return (
        <Link to={'/faculty/' + faculty.id}>
            <Card>
                <span><p><b>{faculty.name} ({faculty.abbreviation})</b></p></span>
            </Card>
        </Link>
    )
}

export default FacultyCard

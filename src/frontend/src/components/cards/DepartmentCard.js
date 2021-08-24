import { Link } from 'react-router-dom'
import Card from "./Card"

const DepartmentCard = ({ dept }) => {
    return (
        <Link to={'/department/' + dept.id}>
            <Card>
                <div className="card-content-variant-1">
                    <span className="card-title"><p><b>{dept.name}</b></p></span>
                    <span className="card-sub-1"><p>{dept.abbreviation}</p></span>
                    {dept.faculty && <span className="card-sub-3"><p>Факултет: {dept.faculty.abbreviation}</p></span>}
                </div>
            </Card>
        </Link>
    )
}

export default DepartmentCard

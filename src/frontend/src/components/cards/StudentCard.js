import { Link } from 'react-router-dom'
import Card from "./Card"

const StudentCard = ({ student }) => {
    return (
        <Link to={'/student/' + student.id}>
            <Card>
                <div className='card-content-variant-2'>
                    <span className="card-title"><p><b>{student.firstName} {student.middleName} {student.lastName}</b></p></span>
                    <span className="card-sub-1"><p>{student.facultyNumber}</p></span>
                    <span className="card-sub-2" style={{"textAlign": "center"}}><p>{student.academicProgram ? ('Специалност: ' + student.academicProgram.abbreviation) : ('Курс: ' + student.academicYear)}</p></span>
                    <span className="card-sub-3"><p>{student.academicProgram ? ('Курс: ' + student.academicYear) : ('Група: ' + student.studentGroup)}</p></span>
                </div>
            </Card>
        </Link>
    )
}

export default StudentCard;
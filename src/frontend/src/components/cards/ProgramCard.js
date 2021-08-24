import { Link } from 'react-router-dom'
import Card from "./Card"

const ProgramCard = ({ program }) => {
    return (
        <Link to={'/program/' + program.id}>
            <Card>
                {program.department ? (
                    <div className='card-content-variant-2'>
                        <span className="card-title"><p><b>{program.name}</b></p></span>
                        <span className="card-sub-1"><p>{program.abbreviation}</p></span>
                        <span className="card-sub-2"><p>Обучаваща катедра: {program.department.abbreviation}</p></span>
                        <span className="card-sub-3"><p>Поток: {program.academicStream}</p></span>
                    </div>
                ) : (
                    <div className='card-content-variant-1'>
                        <span className="card-title"><p><b>{program.name}</b></p></span>
                        <span className="card-sub-1"><p>{program.abbreviation}</p></span>
                        <span className="card-sub-3"><p>Поток: {program.academicStream}</p></span>
                    </div>
                )}
            </Card>
        </Link>
    )
}

export default ProgramCard

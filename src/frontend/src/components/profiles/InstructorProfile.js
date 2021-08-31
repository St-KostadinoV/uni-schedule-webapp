import {getInstructorTitle, getQualification, getShortAcademicDegree} from "../../util";
import ChangePassForm from "../forms/ChangePassForm";
import {useState} from "react";

const InstructorProfile = ({ instructor }) => {
    const [showPassFrom, setShowPassForm] = useState(false);

    return (
        <>
            <h3 className='alt'>{instructor.honoraryStatus ? 'хоноруван ' : ''}{getInstructorTitle(instructor.title)}</h3>
            <h2 className='alt'><b>{getShortAcademicDegree(instructor.degree)} {getQualification(instructor.qualification)} {instructor.firstName} {instructor.middleName} {instructor.lastName}</b></h2>
            <h4 className='alt'><b>Член на катедра: </b>{instructor.department && instructor.department.name}</h4>
            <div className='contact-info'>
                <p className='alt'><b>Кабинет: </b>{instructor.office}</p>
                <p className='alt'><b>E-mail адрес: </b>{instructor.email}</p>
                <p className='alt'><b>Телефон за връзка: </b>{instructor.phone}</p>
            </div>
            <div className='profile-buttons'>
                <button onClick={() => setShowPassForm(!showPassFrom)}>Смени парола</button>
                <button >Смени e-mail</button>
                <button >Дневен разпис</button>
                <button >Седмичен разпис</button>
            </div>
            {showPassFrom && <ChangePassForm />}
        </>
    )
}

export default InstructorProfile
import ChangePassForm from "../forms/ChangePassForm";
import {useState} from "react";

const StudentProfile = ({ student }) => {
    const [showPassFrom, setShowPassForm] = useState(false);

    return (
        <>
            <div className='side-content'>
                <h2 className='alt'><b>{student.firstName} {student.middleName} {student.lastName}</b></h2>
                <h4 className='alt'><b>Факултетен номер: </b>{student.facultyNumber}</h4>
                <h4 className='alt'><b>Специалност: </b>{student.academicProgram && student.academicProgram.name}</h4>
                <p className='alt'><b>Курс: </b>{student.academicYear}</p>
                <p className='alt'><b>Група: </b>{student.studentGroup}</p>
                <p className='alt'><b>Активен: </b>{student.activeStatus ? 'Да' : 'Не'}</p>
                <div className='contact-info'>
                    <p className='alt'><b>E-mail адрес: </b>{student.email}</p>
                    <p className='alt'><b>Телефон за връзка: </b>{student.phone}</p>
                </div>
                <div className='profile-buttons'>
                    <button onClick={() => setShowPassForm(!showPassFrom)}>Смени парола</button>
                    <button >Дневен разпис</button>
                    <button >Седмичен разпис</button>
                </div>
                {showPassFrom && <ChangePassForm />}
            </div>
        </>
    )
}

export default StudentProfile
import SidePanel from "../SidePanel";

const StudentProfile = ({ student }) => {
    return (
        <>
            <SidePanel>
                <p className='menu-option'>Профил</p>
                <p className='menu-option'>Разпис за деня</p>
                <p className='menu-option'>Седмичен разпис</p>
                <p className='menu-option'>Промяна на парола</p>
            </SidePanel>
            <div className='side-content'>
                <h2 className='alt'><b>{student.firstName} {student.middleName} {student.lastName}</b></h2>
                <h4 className='alt'><b>Факултетен номер: </b>{student.facultyNumber}</h4>
                <h4 className='alt'><b>Специалност: </b>{student.academicProgram && student.academicProgram.name}</h4>
                <p className='alt'><b>Курс: </b>{student.academicYear}</p>
                <p className='alt'><b>Група: </b>{student.studentGroup}</p>
                <p className='alt'><b>Активен: </b>{student.activeStatus ? 'Да' : 'Не'}</p>
                <div style={{marginTop: '24px'}}>
                    <p className='alt'><b>E-mail адрес: </b>{student.email}</p>
                    <p className='alt'><b>Телефон за връзка: </b>{student.phone}</p>
                </div>
            </div>
        </>
    )
}

export default StudentProfile
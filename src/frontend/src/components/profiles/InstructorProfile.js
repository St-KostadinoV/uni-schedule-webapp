import { getInstructorTitle } from "../../util"
import SidePanel from "../SidePanel";

const InstructorProfile = ({ instructor }) => {
    return (
        <>
            <SidePanel>
                <p className='menu-option'>Профил</p>
                <p className='menu-option'>Разпис за деня</p>
                <p className='menu-option'>Седмичен разпис</p>
                <p className='menu-option'>Промяна на e-mail</p>
                <p className='menu-option'>Промяна на парола</p>
            </SidePanel>
            <div className='side-content'>
                <h3 className='alt'>{instructor.honoraryStatus ? 'хоноруван ' : ''}{getInstructorTitle(instructor.title)}</h3>
                <h2 className='alt'><b>{instructor.firstName} {instructor.middleName} {instructor.lastName}</b></h2>
                <h4 className='alt'><b>Член на катедра: </b>{instructor.department && instructor.department.name}</h4>
                <div style={{marginTop: '24px'}}>
                    <p className='alt'><b>Кабинет: </b>{instructor.office}</p>
                    <p className='alt'><b>E-mail адрес: </b>{instructor.email}</p>
                    <p className='alt'><b>Телефон за връзка: </b>{instructor.phone}</p>
                </div>
            </div>
        </>
    )
}

export default InstructorProfile

import {useState} from "react";
import FacultyCardList from "../cards/admin-cards/FacultyCardList";

const FrontDeskProfile  = () => {
    const [showFaculties, setShowFaculties] = useState(false);

    return (
        <>
            <h2 className='alt'><b>Фронт офис</b></h2>
            <div className='profile-buttons' style={{marginBottom: 0}}>
                <button onClick={() => setShowFaculties(!showFaculties)}>Факултети</button>
                <button >Катедри</button>
                <button >Специалности</button>
            </div>
            <div className='profile-buttons' style={{marginTop: 0}}>
                <button style={{marginTop: 0}}>Преподаватели</button>
                <button style={{marginTop: 0}}>Дисциплини</button>
                <button style={{marginTop: 0}}>Студенти</button>
            </div>
            {showFaculties && <FacultyCardList/>}
        </>
    )
}

export default FrontDeskProfile
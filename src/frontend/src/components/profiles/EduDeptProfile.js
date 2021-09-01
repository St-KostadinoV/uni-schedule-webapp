import FilterForm from "../forms/FilterForm";
import {useState} from "react";
import TimetableForm from "../forms/admin-forms/TimetableForm";
import TimetableCardList from "../cards/admin-cards/TimetableCardList";

const EduDeptProfile = () => {
    const [showForm, setShowForm] = useState(false);

    return (
        <>
            <FilterForm>
                <h2><b>Учебен отдел - Разписи</b></h2>
                <button className='add' onClick={() => setShowForm(!showForm)}>Добави</button>
            </FilterForm>
            {showForm && <TimetableForm/>}
            <TimetableCardList />
        </>
    )
}

export default EduDeptProfile
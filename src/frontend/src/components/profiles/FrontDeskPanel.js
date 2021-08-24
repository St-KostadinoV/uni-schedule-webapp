import SidePanel from "../SidePanel";

const FrontDeskPanel = () => {
    return (
        <>
            <SidePanel>
                <p className='menu-option'>Факултети</p>
                <p className='menu-option'>Катедри</p>
                <p className='menu-option'>Преподаватели</p>
                <p className='menu-option'>Специалности</p>
                <p className='menu-option'>Дисциплини</p>
                <p className='menu-option'>Студенти</p>
                {/*<p className='menu-option'>Статистика</p>*/}
            </SidePanel>
            <div className='side-content'>

            </div>
        </>
    )
}

export default FrontDeskPanel
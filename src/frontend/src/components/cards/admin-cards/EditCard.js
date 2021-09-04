import '../../../styles/card.css'
import edit from '../../../resources/icons8_edit_32px.png'
import del from '../../../resources/icons8_waste_32px.png'

const EditCard = ({ children, itemId, onDelete, onEdit }) => {
    return (
        <div className='card'>
            <div className='edit-card'>
                <div className='edit-card-content'>
                    {children}
                </div>
                <div className='edit-card-content'>
                    <img src={del} width='32px' height='32px' onClick={() => onDelete(itemId)}/>
                    <img src={edit} width='32px' height='32px' onClick={() => onEdit(itemId)}/>
                </div>
            </div>
        </div>
    )
}

export default EditCard
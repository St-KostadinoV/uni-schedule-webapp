import Form from '../Form'
import {useState} from "react";

const FacultyForm = ({onAdd}) => {
    const [name, setName] = useState('')
    const [abbrev, setAbbrev] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault()

        if(!name || !abbrev){
            alert('Моля, попълнете всички полета!')
            return
        }

        let faculty = {
            name: name,
            abbreviation: abbrev
        }

        onAdd(faculty)
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='form-fields'>
                <p>
                    <label>Име: </label>
                    <input type='text' value={name} onChange={event => setName(event.target.value)}/>
                </p>
                <p>
                    <label>Абревиатура: </label>
                    <input type='text' value={abbrev} onChange={event => setAbbrev(event.target.value)}/>
                </p>
            </div>
            <input type="submit" value="ДОБАВИ"/>
        </Form>
    )
}

export default FacultyForm;
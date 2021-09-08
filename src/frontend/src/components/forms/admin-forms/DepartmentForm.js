import Form from '../Form'
import {useEffect, useState} from "react";

const DepartmentForm = ({onAdd}) => {
    const [name, setName] = useState('')
    const [abbrev, setAbbrev] = useState('')
    const [faculty, setFaculty] = useState('')
    const [faculties, setFaculties] = useState('')

    useEffect(() => {
        const getFaculties = async () => {
            const facultiesFromServer = await fetchFaculties()
            setFaculties(facultiesFromServer)
        }

        getFaculties()
    }, [])

    const fetchFaculties = async () => {
        const res = await fetch('http://localhost:8080/faculties')
        return await res.json()
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        if(!name || !abbrev || !faculty){
            alert('Моля, попълнете всички полета!')
            return
        }

        let department = {
            name: name,
            abbreviation: abbrev,
            faculty: {
                id: faculty
            }
        }

        onAdd(department)
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
                <p>
                    <label>Факултет: </label>
                    <select className='alt' onChange={event => setFaculty(event.target.value)}>
                        <option value=''> - Изберете факултет - </option>
                        {
                            faculties.length > 0 && faculties.map( f => (
                                <option key={f.id} value={f.id}>{f.abbreviation}</option>
                            ))
                        }
                    </select>
                </p>
            </div>
            <input type="submit" value="ДОБАВИ"/>
        </Form>
    )
}

export default DepartmentForm
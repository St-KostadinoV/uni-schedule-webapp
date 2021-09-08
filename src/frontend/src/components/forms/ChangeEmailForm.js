import {useState} from "react";
import authHeader from "../../services/auth-header";
import Form from "./Form";

const ChangeEmailForm = ({ onSubmit }) => {
    const [email, setEmail] = useState('')

    const changeEmail = async (email) => {
        const res = await fetch('http://localhost:8080/profile/email-change', {
            method: 'POST',
            headers: {
                ...authHeader(),
                'Content-type': 'application/json'
            },
            body: JSON.stringify({email})
        })

        if(!res.ok)
            throw new Error()
        else {
            alert('Успешно сменихте e-mail адреса си!')
            window.location.reload()
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        if(email) {
            setEmail('')
            changeEmail(email)
                .catch(err => {
                    alert(err)
                })
            onSubmit()
        } else
            alert('Моля, попълнете полето!')
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='form-fields'>
                <p>Нов e-mail адрес: <input type="text" value={email} onChange={event => setEmail(event.target.value)}/></p>
            </div>
            <input type="submit" value="Потвърди" />
        </Form>
    )
}

export default ChangeEmailForm;
import Form from './Form'
import authHeader from "../../services/auth-header";
import {useState} from "react";

const ChangePassForm = ({ onSubmit }) => {
    const [oldPassword, setOldPassword] = useState('')
    const [newPassword, setNewPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')

    const changePass = async (param) => {
        const res = await fetch('http://localhost:8080/profile/pass-change', {
            method: 'POST',
            headers: {
                ...authHeader(),
                'Content-type': 'application/json'
            },
            body: JSON.stringify(param)
        })

        if(!res.ok)
            throw new Error()
        else
            alert('Успешно сменихте паролата си!')
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        if(oldPassword && newPassword && confirmPassword) {
            if(newPassword === confirmPassword) {
                setOldPassword('')
                changePass({newPassword, oldPassword})
                    .catch(err => {
                        alert(err)
                    })
                onSubmit()
            } else
                alert('Нововъведените пароли не съвпадат!')

            setNewPassword('')
            setConfirmPassword('')
        } else
            alert('Моля, попълнете всички полета!')
    }

    return (
        <Form onSubmit={handleSubmit}>
          <div className='form-fields'>
            <p>Текуща парола: <input type="password" value={oldPassword} onChange={event => setOldPassword(event.target.value)}/></p>
            <p>Нова парола: <input type="password" value={newPassword} onChange={event => setNewPassword(event.target.value)}/></p>
            <p>Потвърдете паролата: <input type="password" value={confirmPassword} onChange={event => setConfirmPassword(event.target.value)}/></p>
          </div>
          <input type="submit" value="Потвърди" />
        </Form>
    )
}

export default ChangePassForm
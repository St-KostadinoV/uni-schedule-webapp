import { useState } from 'react';
import logo from '../../resources/logo-blue.png';
import Form from './Form'

import authService from '../../services/auth-service'
import {Redirect, useHistory} from 'react-router-dom';

const LoginForm = () => {
    const [username, setUsername] = useState([]);
    const [password, setPassword] = useState([]);
    const history= useHistory();

    const isLoggedIn = authService.getCurrentUser();

    const handleLogin = (e) => {
        e.preventDefault();

        authService
            .login(username, password)
            .then(() => {
                history.push("/user")
                window.location.reload()
            })
    }

  return (
      <>
          {isLoggedIn ? (
              <Redirect to='/user' />
          ) : (
              <Form style={{ maxWidth: '288px', margin: '32px auto' }} onSubmit={handleLogin}>
                  <img className='login-logo' src={logo} width='194' height='194'/>
                  <p className='login-label'>Потребителско име:</p>
                  <input type="text" value={username} onChange={e => (setUsername(e.target.value))}/>
                  <p className='login-label'>Парола:</p>
                  <input type="password" value={password} onChange={e => (setPassword(e.target.value))}/>
                  {/*<p style={{ textAlign: 'left' }}><input style={{ marginRight: '8px' }} type="checkbox" />Запомни ме</p>*/}
                  <input type="submit" value="ВХОД"/>
              </Form>
          )}
      </>
  )
}

export default LoginForm

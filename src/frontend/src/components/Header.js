import { Link } from 'react-router-dom';
import { useState } from 'react'
import logo from '../resources/logo-white.png';
import '../styles/header.css';
import authService from "../services/auth-service";

const Header = () => {
    const [currentUser, setCurrentUser] = useState(authService.getCurrentUser())

    const getUserLink = () => {
        switch(currentUser.roles[0]){
            case 'ROLE_FRONT_DESK':
                return 'Фронт офис'
            case 'ROLE_EDUCATION_DEPT':
                return 'Учебен отдел'
            default:
                return 'Профил'
        }
    }

    return (
        <header className="header">
            <div className="container">
                <div className="logo">
                    <Link to="/"><img src={logo} width="96" height="96"/></Link>
                </div>
                <div className="title">
                    <h1>Технически Университет - Варна</h1>
                </div>
                <div className="login-logout">
                    {currentUser ? (
                        <div style={{display: "inline-flex"}}>
                            <Link to="/user"><p className="link">{getUserLink()}</p></Link>
                            <p className="link">•</p>
                            <a href="/" onClick={authService.logout}><p className="link">Изход</p></a>
                        </div>
                    ) : (
                        <div style={{display: "inline-flex"}}>
                            <Link to="/login"><p className="link">Вход</p></Link>
                        </div>
                    )}
                </div>
            </div>
        </header>
    );
}

export default Header;
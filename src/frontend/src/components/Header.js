import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react'
import logo from '../resources/logo-white.png';
import '../styles/header.css';
import authService from "../services/auth-service";

const Header = () => {
    const [currentUser, setCurrentUser] = useState([])

    useEffect(() => {
        setCurrentUser(authService.getCurrentUser())
    }, [])

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
                            <Link to="/profile"><p className="link">Профил</p></Link>
                            <p className="link">•</p>
                            <a href="/logout" onClick={authService.logout}><p className="link">Изход</p></a>
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
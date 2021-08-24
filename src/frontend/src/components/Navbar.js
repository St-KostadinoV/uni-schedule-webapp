import { Link } from 'react-router-dom'
import '../styles/navbar.css'

const Navbar = () => {
    return (
        <div className='navbar'>
            <Link className='navlink' to='/faculties'><p>ФАКУЛТЕТИ</p></Link>
            <Link className='navlink' to='/departments'><p>КАТЕДРИ</p></Link>
            <Link className='navlink' to='/programs'><p>СПЕЦИАЛНОСТИ</p></Link>
            <Link className='navlink' to='/disciplines'><p>ДИСЦИПЛИНИ</p></Link>
            <Link className='navlink' to='/instructors'><p>ПРЕПОДАВАТЕЛИ</p></Link>
            <Link className='navlink' to='/students'><p>СТУДЕНТИ</p></Link>
        </div>
    )
}

export default Navbar

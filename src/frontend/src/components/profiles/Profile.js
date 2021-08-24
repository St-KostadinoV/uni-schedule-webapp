import InstructorProfile from "./InstructorProfile"
import userService from "../../services/user-service.js"
import { useState, useEffect } from "react";
import authService from "../../services/auth-service";
import StudentProfile from "./StudentProfile";

const Profile = () => {
    const [user, setUser] = useState([])

    useEffect(() => {
        userService
            .getUserProfile()
            .then(
                (response) => {
                    setUser(response.data)
                }
            )
    }, [])

    function BuildProfile() {
        let currentUser = authService.getCurrentUser()
        switch(currentUser.roles[0]) {
            case 'ROLE_INSTRUCTOR':
                return <InstructorProfile instructor={user}/>
            case 'ROLE_FRONT_END':
            //return <InstructorProfile instructor={user}/>
            case 'ROLE_EDUCATION_DEPT':
            //return <InstructorProfile instructor={user}/>
            default:
                return <StudentProfile student={user}/>
        }
    }

    return (
        <div className='page-with-menu'>
            {user && BuildProfile()}
        </div>
    )
}

export default Profile

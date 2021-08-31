import userService from "../../services/user-service.js"
import { useState, useEffect } from "react";
import authService from "../../services/auth-service";
import StudentProfile from "./StudentProfile";
import InstructorProfile from "./InstructorProfile";
import FrontDeskProfile from "./FrontDeskProfile";
import EduDeptProfile from "./EduDeptProfile";

const UserBoard = () => {
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
            case 'ROLE_FRONT_DESK':
                return <FrontDeskProfile />
            case 'ROLE_EDUCATION_DEPT':
                return <EduDeptProfile />
            case 'ROLE_STUDENT':
                return <StudentProfile student={user}/>
        }
    }

    return (
        <>
            {user && BuildProfile()}
        </>
    )
}

export default UserBoard

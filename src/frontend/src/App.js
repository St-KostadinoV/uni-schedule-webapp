import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Footer from './components/Footer'
import Header from './components/Header'
import Navbar from './components/Navbar'
import FacultiesList from './components/entity-lists/FacultiesList'
import DepartmentsList from './components/entity-lists/DepartmentsList'
import ProgramsList from './components/entity-lists/ProgramsList'
import DisciplinesList from './components/entity-lists/DisciplinesList'
import InstructorsList from './components/entity-lists/InstructorsList'
import FacultyDetails from './components/entity-details/FacultyDetails'
import DepartmentDetails from './components/entity-details/DepartmentDetails'
import ProgramDetails from './components/entity-details/ProgramDetails'
import DisciplineDetails from './components/entity-details/DisciplineDetails'
import InstructorDetails from './components/entity-details/InstructorDetails'
import LoginForm from './components/forms/LoginForm'
import { useState, useEffect } from 'react'
import UserBoard from './components/profiles/UserBoard'
import authService from './services/auth-service'
import StudentsList from './components/entity-lists/StudentsList'
import StudentDetails from './components/entity-details/StudentDetails'
import Logout from "./components/Logout";

function App() {
  const [currentUser, setCurrentUser] = useState([])

  useEffect(() => {
    const user = authService.getCurrentUser()

    if(user) {
      setCurrentUser(user)
    }
  }, [])

  return (
    <Router>
      <Header />
      <Navbar />
      <div className='centered-content'>
        <Switch>
          <Route path="/login" children={<LoginForm />} />
          <Route path="/logout" children={<Logout />} />
          <Route path="/user" children={<UserBoard />} />
          <Route path="/faculties" children={<FacultiesList />} />
          <Route path="/faculty/:id" children={<FacultyDetails />} />
          <Route path="/departments" children={<DepartmentsList />} />
          <Route path="/department/:id" children={<DepartmentDetails />} />
          <Route path="/programs" children={<ProgramsList />} />
          <Route path="/program/:id" children={<ProgramDetails />} />
          <Route path="/disciplines" children={<DisciplinesList />} />
          <Route path="/discipline/:id" children={<DisciplineDetails />} />
          <Route path="/instructors" children={<InstructorsList />} />
          <Route path="/instructor/:id" children={<InstructorDetails />} />
          <Route path="/students" children={<StudentsList />} />
          <Route path="/student/:id" children={<StudentDetails />} />
        </Switch>
      </div>
      <Footer />
    </Router>
  );
}

export default App;

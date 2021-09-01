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
import PrivateRoute from "./components/PrivateRoute";

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
          <Route path="/login" component={LoginForm} />
          <PrivateRoute path="/user" component={UserBoard} />
          <Route path="/faculties" component={FacultiesList} />
          <Route path="/faculty/:id" component={FacultyDetails} />
          <Route path="/departments" component={DepartmentsList} />
          <Route path="/department/:id" component={DepartmentDetails} />
          <Route path="/programs" component={ProgramsList} />
          <Route path="/program/:id" component={ProgramDetails} />
          <Route path="/disciplines" component={DisciplinesList} />
          <Route path="/discipline/:id" component={DisciplineDetails} />
          <Route path="/instructors" component={InstructorsList} />
          <Route path="/instructor/:id" component={InstructorDetails} />
          <Route path="/students" component={StudentsList} />
          <Route path="/student/:id" component={StudentDetails} />
        </Switch>
      </div>
      <Footer />
    </Router>
  );
}

export default App;

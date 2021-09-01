import {Redirect, Route} from "react-router-dom";
import authService from "../services/auth-service"

const PrivateRoute = ({ component: Component, ...rest }) => {

    const isLoggedIn = authService.getCurrentUser();

    return (
        <Route
            {...rest}
            render={props =>
                isLoggedIn ? (
                    <Component {...props} />
                ) : (
                    <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
                )
            }
        />
    )
}

export default PrivateRoute
import axios from 'axios';

const login = (username, password) => {
    return axios
        .post('http://localhost:8080/login', {
                username,
                password
            })
        .then( response => {
            if(response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data))
            }
            return response.data
        })
}

const logout = () => {
    localStorage.removeItem("user");
    alert("Успешно излязохте от системата!");
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
}

export default { 
    login, 
    logout, 
    getCurrentUser 
};
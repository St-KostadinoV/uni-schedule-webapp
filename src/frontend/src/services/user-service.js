import axios from 'axios';
import authHeader from './auth-header';

const getUserProfile = () => {
    return axios.get('http://localhost:8080/profile', { headers: authHeader() })
}

export default { getUserProfile };
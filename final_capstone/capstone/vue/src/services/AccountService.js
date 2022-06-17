import axios from 'axios';

export default {

    getBalance() {
        return axios.get('tenmo/balance')
    },

    getUsers() {
        return axios.get('tenmo/users')
    }
}
import axios from 'axios';

export default {

    getBalance() {
        return axios.get('tenmo/balance')
    }
}
import axios from 'axios';

export default {

    getTransfers() {
        return axios.get('tenmo/transfers')
    },

    sendTransfer(transfer) {
        return axios.post('/tenmo/send', transfer)
    }

}
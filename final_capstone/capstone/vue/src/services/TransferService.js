import axios from 'axios';

export default {

    getTransfers() {
        return axios.get('tenmo/transfer/list')
    },

    sendTransfer(transfer) {
        return axios.post('/tenmo/transfer/send', transfer)
    },

    requestTransfer(transfer) {
        return axios.post('tenmo/transfer/request', transfer)
    },

    approveTransfer(transfer) {
        return axios.put('tenmo/transfer/approve', transfer)
    },

    declineTransfer(transfer) {
        return axios.put('tenmo/transfer/decline', transfer)
    }

}
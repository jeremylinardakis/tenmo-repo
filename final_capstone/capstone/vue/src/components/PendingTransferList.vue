<template>
  <div class="pending-transfer-list-container">
    <h5>{{pendingTransfers}}</h5>
    <b-card
        v-for="transfer in pendingTransfers"
        v-bind:key="transfer.id"
        border-variant="secondary"
        :header="transfer.fromUsername"
        header-border-variant="secondary"
        align="center"
    >
      <b-card-text>Amount: ${{transfer.transferAmount}}</b-card-text>
      <b-button v-on:click="approveTransfer(transfer)">Approve request</b-button>
      <b-button v-on:click="declineTransfer(transfer)">Decline request</b-button>
    </b-card>
  </div>
</template>

<script>

import transferService from "@/services/TransferService";

export default {
  name: "PendingTransferList",
  data() {
    return {
      pendingTransfers : []
    }
  },
  created() {
    console.log(this.$store.state.user.username)
      this.pendingTransfers = this.$store.state.transfers.filter(transfer => {
        return (transfer.transferType === 2 && this.$store.state.user.username === transfer.toUsername)
      })



  },
  methods: {
    approveTransfer(transfer) {
      transferService.approveTransfer(transfer).then(response => {
        console.log(response)
      })
    },
    declineTransfer(transfer) {
      transferService.declineTransfer(transfer).then(response => {
        console.log(response)
      })
    }
  }
}
</script>

<style scoped>

</style>
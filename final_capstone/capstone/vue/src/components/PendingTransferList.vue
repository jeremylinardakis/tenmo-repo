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
        return (transfer.transferStatus === 1 && this.$store.state.user.username !== transfer.fromUsername)
      })
  },
  methods: {
    approveTransfer(transfer) {
      transferService.approveTransfer(transfer).then(response => {
        if (response.status === 200) {
          alert("Transfer successfully approved!")
          this.$router.push({ path : '/'})
        }
      })
    },
    declineTransfer(transfer) {
      transferService.declineTransfer(transfer).then(response => {
        if (response.status === 200) {
          alert("Transfer successfully decline!")
          this.$router.push({ path : '/'})
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
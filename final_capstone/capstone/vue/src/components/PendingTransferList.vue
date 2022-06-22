<template>
  <div class="pending-transfer-list-container">
    <h2>my pending transfers</h2>
    <h5 id="empty-array" v-if="pendingTransfers.length === 0">no transfers to display(yet)</h5>
    <b-card
        id="transfer-card"
        v-for="transfer in pendingTransfers"
        v-bind:key="transfer.id"
        border-variant="secondary"
        :header="headerMessage(transfer.toUsername)"
        header-border-variant="secondary"
        align="center"
    >
      <b-card-text>Requested amount: ${{transfer.transferAmount}}</b-card-text>
      <b-card-text>Message: {{transfer.transferMessage}}</b-card-text>
      <b-button id="btn" v-on:click="approveTransfer(transfer)">Approve request</b-button>
      <b-button id="btn" v-on:click="declineTransfer(transfer)">Decline request</b-button>
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
        return (transfer.transferStatus === 1 && this.$store.state.user.id !== transfer.userIdOfDestinationAccount)
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
    },
    headerMessage(username) {
      return "From: " + username
    }
  }
}
</script>

<style scoped>
#transfer-card {
  max-width: 50%;
  margin: 5px;
  box-shadow: 2px 2px;
}

.pending-transfer-list-container {
  display: flex;
  flex-direction: column;
  align-items: center;

}

#btn {
  margin: 2px;
  box-shadow: 2px 2px black;

}

#btn:hover {
  box-shadow: none;
}

h2 {
  margin: 20px;
  font-weight: bold;
}
</style>
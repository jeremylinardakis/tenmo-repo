<template>
  <div class="transfer-list-container">
    <h1 id="header">transfer history <p id="sub-header">[ click on a transfer to see more details ]</p></h1>
    <h5 id="empty-array" v-if="transfers.length === 0">oops!<br>no transfers to display(yet)</h5>
    <div id="transfer-item" v-for="transfer in transfers" :key="transfer.id">
      <div id="individual-transfer" v-on:click="nextPage(transfer.transferId)">{{getSummary(transfer)}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TransferList",
  data() {
    return {
      transfers : [],
    }
  },
  created() {
    this.transfers = this.$store.state.transfers
  },
  methods: {
    getSummary(transfer) {
      if(transfer.fromUsername === this.$store.state.user.username) {
        if(transfer.transferType === 2) {
          return transfer.toUsername + " requested $" + transfer.transferAmount + " from you"
        } else if (transfer.transferType === 1) {
          return "you sent " + transfer.toUsername + " $" + transfer.transferAmount + " dollars"
        }
      } else if (transfer.fromUsername !== this.$store.state.username){
        if (transfer.transferType === 2) {
          return "you requested " + " $" + transfer.transferAmount + " dollars"+ " from " + transfer.toUsername
        } else if (transfer.transferType === 1){
          return transfer.fromUsername + " sent you $" + transfer.transferAmount + " dollars"
        }
      }
    },
    nextPage(transferId) {
      this.$router.push({name : 'transfer-details', params : {transferId: transferId}})
    }

  }
}
</script>

<style scoped>
#individual-transfer:hover {
  cursor: pointer;
  font-weight: bold;
}

#individual-transfer {
  margin: 5px;
  border-radius: 5px;
  padding: 5px;
}

#transfer-item {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 10%;
}

#header {
  margin-left: 10%;
  font-weight: bold;
  margin-bottom: 20px;
}

#sub-header {
  font-size: 14px;
  margin-top: 10px;
}

#empty-array {
  margin-top: 4%;
  margin-left: 15%;
}
</style>
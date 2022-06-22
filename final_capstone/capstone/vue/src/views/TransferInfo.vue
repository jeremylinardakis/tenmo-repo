<template>
  <div class="transfer-container">
    <h2>transfer details</h2>
    <ul id="detail-container">
      <li>Who sent it: {{fromUser}}</li>
      <li>Who received it: {{toUser}}</li>
      <li>How much was sent: ${{transfer.transferAmount}}</li>
      <li>Message: {{transfer.transferMessage}}</li>
      <li>Status: {{getStatus()}}</li>
      <li>Transfer type: {{getType()}}</li>
    </ul>
    <b-button v-on:click="goBack()">Back</b-button>
  </div>
</template>

<script>
export default {
  name: "TransferInfo",
  data() {
    return {
      type: '',
      status: '',
      fromUser: '',
      toUser: ''
    }
  },
  computed: {
    transfer() {
      return this.$store.state.transfers.find(transfer => {
        return transfer.transferId === this.$route.params.transferId
      })
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      if (this.$store.state.user.username === this.transfer.fromUsername) {
        this.fromUser = "you"
        this.toUser = this.transfer.toUsername
      } else {
        this.fromUser = this.transfer.fromUsername
        this.toUser = "you"
      }
    },
    getStatus() {
      if (this.transfer.transferStatus === 1) {
        return "Pending"
      } else if (this.transfer.transferStatus === 2 ) {
        return "Approved"
      } else {
        return "Declined"
      }
    },
    getType() {
      if (this.transfer.transferType === 1) {
        return "Sent funds"
      } else {
        return "Requested funds"
      }
    },
    goBack() {
      this.$router.push({path : "/transfers"})
    }
  }
}
</script>

<style scoped>
.transfer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

li {
  list-style: none;
}

#detail-container {
  border : solid black 1px;
  margin: 10px;
  padding : 20px;
  border-radius: 5px;
  box-shadow: 2px 2px;
}
</style>
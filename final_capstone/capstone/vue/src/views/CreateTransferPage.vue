<template>
  <div class="transfer-form-container">
    <b-button v-on:click="toggleSendTransfer()" v-if="transfer.transferType === ''">Send</b-button>
    <b-button v-on:click="toggleRequestTransfer()" v-if="transfer.transferType === ''">Request</b-button>
    <h3>Available balance: ${{balance}}</h3>
    <h3>{{displayTransferType()}}</h3>
    <h3>{{user}}</h3>
    <h3>{{transfer}}</h3>
    <b-form-group v-if="transfer.transferType !== ''">
    <b-form-input :disabled="transfer.transferType === ''" :maxlength="balance" type="number" placeholder="amount" v-model="transfer.transferAmount"></b-form-input>
    <b-form-textarea
        :disabled="transfer.transferType === ''"
        id="textarea"
        v-model="transfer.transferMessage"
        placeholder="Enter a transfer message!"
        rows="3"
        max-rows="6"
    ></b-form-textarea>
    <b-button v-on:click="sendTransfer()">Send</b-button>
    <b-button v-on:click="cancelRequest()">Cancel</b-button>
    </b-form-group>
  </div>
</template>

<script>
import transferService from "@/services/TransferService";

export default {
  name: "CreateTransferPage",
  data() {
    return {
      balance : '',
      transfer: {
        toUsername : '',
        userIdOfDestinationAccount : '',
        transferStatus : '',
        transferType : '',
        transferMessage : '',
      },
      transferAmount : '',
      transferMessage : '',
    }
  },
  methods: {
    sendTransfer() {
      this.transfer.toUsername = this.user.username;
      this.transfer.userIdOfDestinationAccount = this.user.id;
      if (this.transfer.transferType === 1) {
        this.transfer.transferStatus = 2;
        transferService.sendTransfer(this.transfer).then(response => {
          if (response.data === true) {
            this.balance -= this.transferAmount
            this.$store.commit("SET_USER_BALANCE", this.balance)
            alert("Funds were sent successfully!")
            this.$router.push({path : '/'})
          }
        })
      } else {
        this.transfer.transferStatus = 1
        transferService.requestTransfer(this.transfer).then(response => {
          console.log(response.data)
        })
      }
    },
    toggleSendTransfer() {
      this.transfer.transferType = 1
    },
    toggleRequestTransfer() {
      this.transfer.transferType = 2
    },
    displayTransferType() {
      if (this.transfer.transferType === 1) {
        return "Amount to be sent:"
      } else {
        return "Request amount:"
      }
    },
    cancelRequest() {
      this.$router.push({path: '/'})
    }
  },
  created() {
    this.balance = this.$store.state.balance
  },
  computed: {
    user() {
      return this.$store.state.users.find(user => {
        return user.username === this.$route.params.username
      })
    }
  }
}
</script>

<style scoped>

</style>
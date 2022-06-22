<template>
  <div class="transfer-form-container">

    <h2>${{balance}}</h2>
    <h6>[ your available funds ]</h6>
    <h4 v-if="transfer.transferType === ''">what type of request will this be?</h4>
    <div class="button-container">
      <b-button id="btn" v-on:click="toggleSendTransfer()" v-if="transfer.transferType === ''">send them money</b-button>
      <b-button id="btn" v-on:click="toggleRequestTransfer()" v-if="transfer.transferType === ''">request some money</b-button>
    </div>
<!--    <h3>{{displayTransferType()}}</h3>-->

    <b-form-group v-if="transfer.transferType !== ''" id="form-container">
    <b-form-input :disabled="transfer.transferType === ''" :maxlength="balance" type="number" :placeholder="getTransferTypeMessage()" v-model="transfer.transferAmount"></b-form-input>
    <b-form-textarea
        :disabled="transfer.transferType === ''"
        id="textarea"
        v-model="transfer.transferMessage"
        placeholder="enter a transfer message!"
        rows="3"
        max-rows="6"
    ></b-form-textarea>

    </b-form-group>
    <div >
      <b-button id="btn" v-if="transfer.transferType !== ''" v-on:click="sendTransfer()">Send</b-button>
      <b-button id="btn" v-if="transfer.transferType !== ''" v-on:click="cancelRequest()">Cancel</b-button>
    </div>
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
      this.transfer.userIdOfDestinationAccount = this.user.id;
      if (this.transfer.transferType === 1) {
        this.transfer.transferStatus = 2;
        transferService.sendTransfer(this.transfer).then(response => {
          if (response.data === true) {
            alert("Funds were sent successfully!")
            this.$router.push({path : '/'})
          }
        })
      } else {
        this.transfer.transferStatus = 1
        transferService.requestTransfer(this.transfer).then(response => {
          if (response.data === true) {
            alert("Request was sent successfully!")
            this.$router.push({path : '/'})
          }
        })
      }
    },
    toggleSendTransfer() {
      this.transfer.transferType = 1
    },
    toggleRequestTransfer() {
      this.transfer.transferType = 2
    },
    getTransferTypeMessage() {
      if (this.transfer.transferType === 1) {
        return "how much will you send?"
      } else {
        return "how much should they send?"
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
.transfer-form-container {
  display : flex;
  flex-direction: column;
  align-items: center;
}

h2 {
  margin-top: 40px;
  font-size: 50px;
  font-weight: bold;
}

h4 {
  margin: 10px 0px 20px 0px;
}

#btn {
  margin : 0 5px 0 5px
}

#form-container {
  margin: 20px;
  min-width: 25%;
}
</style>
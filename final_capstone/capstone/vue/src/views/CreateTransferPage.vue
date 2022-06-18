<template>
  <div class="transfer-form-container">
    <b-form-group
        label="Button style radios with outline-primary variant and size lg"
        v-slot="{ ariaDescribedby }"
    >
      <b-form-radio-group
          id="btn-radios-2"
          v-model="selected"
          :options="options"
          :aria-describedby="ariaDescribedby"
          button-variant="outline-primary"
          size="lg"
          name="radio-btn-outline"
          buttons
      ></b-form-radio-group>
    </b-form-group>
      <b-form-input type="number" :placeholder="sendOrReceive()" v-model="transferAmount"></b-form-input>
    <b-form-textarea
        id="textarea"
        v-model="transferMessage"
        placeholder="Enter a transfer message!"
        rows="3"
        max-rows="6"
    ></b-form-textarea>
    <b-button v-on:click="sendTransfer()">Send it!</b-button>
  </div>
</template>

<script>
import transferService from "@/services/TransferService";

export default {
  name: "CreateTransferPage",
  data() {
    return {
      user : {},
      transfer: {
        toUsername : this.user.username,
        userIdOfDestinationAccount : this.user.id,
        transferStatus : 'Approved',
        transferType : '',
        transferMessage : '',
      },
      selected : 'radio1',
      options: [
        {text: 'Send funds' },
        {text: 'Request funds'}
      ],
      transferAmount : '',
      transferMessage : '',
    }
  },
  methods: {
    sendOrReceive() {
      if (this.selected === 'radio1') {
        return "Amount to be sent:"
      } else {
        return "Request amount: "
      }
    },
    sendTransfer() {
      if (this.selected ==='radio1') {
        this.transfer.transferType = 1;
        transferService.sendTransfer().then(response => {
          console.log(response.data)
        })
      }
    },
    findUser() {
      return this.$store.state.users.find(user => {
        if(user.username === this.$route.params.username) {
          this.user = user
        }
      })
    }
  },
  created() {
    this.balance = this.$store.state.balance
    this.findUser();
  },

}
</script>

<style scoped>

</style>
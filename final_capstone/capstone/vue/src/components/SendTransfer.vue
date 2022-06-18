<template>
  <div class="transfer-container">
    <h3>Available Balance: {{balance}}</h3>
    <b-input></b-input>

  </div>
</template>

<script>
export default {
  name: "SendTransfer",
  data() {
    return {
      balance : '',
      user : {},
      transfer: {
        toUsername : this.user.username,
        userIdOfDestinationAccount : this.user.id,
        transferStatus : 'Approved',
        transferType : 1,
        transferMessage : '',

      }
    }
  },
  created() {
    this.balance = this.$store.state.balance
    this.findUser();
  },
  methods : {

  }
}
</script>

<style scoped>

</style>
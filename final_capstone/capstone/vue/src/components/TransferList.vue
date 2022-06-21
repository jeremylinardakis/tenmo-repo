<template>
  <div class="transfer-list-container">
    <div id="transfer-item" v-for="transfer in transfers" :key="transfer.id">
      <ul>
        <li>{{getOtherUsername(transfer)}} {{transfer.transferAmount}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "TransferList",
  data() {
    return {
      transfers : [],
      fields: [
        { key: this.transfer.toUsername, label: "To username"}
      ]
    }
  },
  created() {
    this.transfers = this.$store.state.transfers
  },
  methods: {
    getOtherUsername(transfer) {
      let phrase = '';

      if(this.$store.state.user.username === transfer.toUsername) {
        phrase = transfer.fromUsername;
      } else {
        phrase = transfer.toUsername;
      }

      if(this.transfer.transferType === 1) {
        phrase += " sent you "
      } else {
        phrase += ""
      }
      return phrase
    },

  }
}
</script>

<style scoped>

</style>
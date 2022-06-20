<template>
  <div class="transfer-list-container">
    <h5>{{transfers}}</h5>
    <b-card
        v-for="transfer in transfers"
        v-bind:key="transfer.id"
        border-variant="secondary"
        :header="transfer.fromUsername"
        header-border-variant="secondary"
        align="center"
    >
      <b-card-text>Amount: ${{transfer.transferAmount}}</b-card-text>
    </b-card>

  </div>
</template>

<script>
export default {
  name: "TransferList",
  data() {
    return {
      transfers : []
    }
  },
  created() {
    this.transfers = this.$store.state.transfers
  }
}
</script>

<style scoped>

</style>
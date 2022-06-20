<template>
  <div class="transfer-history-container">
  <transfer-list></transfer-list>
  </div>
</template>

<script>
import TransferList from "@/components/TransferList";
export default {
  name: "TransferHistory",
  components: {TransferList}
}
</script>

<style scoped>

</style>
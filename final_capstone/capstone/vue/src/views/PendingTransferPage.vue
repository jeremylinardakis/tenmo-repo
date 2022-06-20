<template>
  <div class="pending-transfer-container">
    <pending-transfer-list></pending-transfer-list>
  </div>
</template>

<script>
import PendingTransferList from "@/components/PendingTransferList";
export default {
  name: "PendingTransferPage",
  components: {PendingTransferList}
}
</script>

<style scoped>

</style>
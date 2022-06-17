<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
import accountService from "@/services/AccountService";

export default {
  name: "home",
  data() {
    return {
      balance : ''
    }
  },
  created() {
    this.getBalance();
  },
  methods : {
    getBalance() {
      accountService.getBalance().then(response => {
        this.balance = response.data;
        this.$store.commit("SET_USER_BALANCE", this.balance)
      })
    }
  }
};
</script>

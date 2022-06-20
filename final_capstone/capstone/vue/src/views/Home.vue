<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <user-list></user-list>
  </div>
</template>

<script>
import accountService from "@/services/AccountService";
import transferService from "@/services/TransferService";
import UserList from "@/components/UserList";

export default {
  name: "home",
  components: {UserList},
  data() {
    return {
      balance : '',
      users : []
    }
  },
  created() {
    this.getBalance();
    this.getUsers();
    this.getTransfers();
  },
  methods : {
    getBalance() {
      accountService.getBalance().then(response => {
        this.balance = response.data;
        this.$store.commit("SET_USER_BALANCE", this.balance)
      })
    },
    getUsers() {
      accountService.getUsers().then(response => {
        this.users = response.data;
        this.$store.commit("SET_AVAILABLE_USERS", this.users)
      })
    },
    getTransfers() {
      transferService.getTransfers().then(response => {
        this.$store.commit("SET_ALL_TRANSFERS", response.data)
      })
    }
  }
};
</script>

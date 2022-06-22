<template>
  <div class="home">
    <h2>Hey there {{this.$store.state.user.username}},</h2>
    <h5>welcome to our money transfer app!</h5>
    <b-button v-if="showUsers === false" v-on:click="toggleShowUsers()">let's get started!</b-button>
    <user-list v-if="showUsers === true"></user-list>
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
      users : [],
      showUsers : false
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
    },
    toggleShowUsers() {
      this.showUsers = true;
    }
  }
};
</script>


<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h2 {
  font-weight: bold;
  margin-top: 10%;
}
</style>
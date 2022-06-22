<template>
  <div class="list-container">
    <p id="instructions">please select a user from the list below to send or request funds</p>
    <div v-on:click="nextPage(user.username)" id="user-bubble" v-for="user in this.$store.state.users" v-bind:key="user.id">
      <p>{{user.username}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserList",
  data() {
    return {
      users : [],
      fields: ['username']
    }
  },
  created() {
    this.users = this.$store.state.users
  },
  methods : {
    nextPage(username) {
      this.$router.push({name :'create', params: {username: username}})
    }
  }
}
</script>

<style scoped>
#instructions {
  margin-bottom: 20px;
}

.list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#user-bubble {
  border: solid black 1px;
  border-radius: 5px;
  margin: 5px;
  min-width: 30%;
  box-shadow: 2px 2px;
  padding: 5px;
}

#user-bubble:hover {
  box-shadow: 4px 4px;
  cursor: pointer;

}

p {
  margin: 5px;
  text-align: center;
}

</style>
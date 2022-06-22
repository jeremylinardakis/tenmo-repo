<template>
  <div class="list-container">
    <p id="instructions">please select a user from the list below to send or request funds</p>
    <p id="filter-box-toggle" v-if="showFilterBox === false" v-on:click="toggleShowFilterBox()">[ click me for a search bar ]</p>
    <input v-if="showFilterBox === true" class="filter" type="text" placeholder="type username here" v-model="searchName"/>
    <div  v-on:click="nextPage(user.username)" id="user-bubble" v-for="user in filteredList" v-bind:key="user.id">
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
      fields: ['username'],
      searchName: "",
      showFilterBox: false,
    }
  },
  created() {
    this.users = this.$store.state.users
  },
  methods : {
    nextPage(username) {
      this.$router.push({name :'create', params: {username: username}})
    },
    toggleShowFilterBox() {
      this.showFilterBox = this.showFilterBox === false;
    }
  },
  computed: {
    filteredList() {
      return this.$store.state.users.filter(user => {
          return user.username.toLowerCase().includes(this.searchName.toLowerCase())
        })
    }
  }
}
</script>

<style scoped>
#instructions {
  margin-bottom: 5px;
}

#filter-box-toggle:hover {
  font-weight: bold;
  cursor: pointer;
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

.filter {
  text-align: center;
  margin: 10px 0 15px 0;
}

</style>
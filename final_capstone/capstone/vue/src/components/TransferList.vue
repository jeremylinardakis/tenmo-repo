<template>
  <div class="transfer-list-container">
    <h1 id="header">transfer history </h1>
    <p id="sub-header">[ click on a transfer to see more details ]</p>
    <p id="or"> or </p>
    <p id="search-bar-toggle" v-on:click="toggleShowSearchBar()" v-if="showSearchBar === false">[ click here to filter results ]</p>
    <input v-if="showSearchBar ===true" type="text" class="filter" v-model="searchName" placeholder="type username to here"/>
    <h5 id="empty-array" v-if="transfers.length === 0">oops!<br>no transfers to display(yet)</h5>
    <div id="transfer-item" v-for="transfer in filteredList" :key="transfer.id">
      <div id="individual-transfer" v-on:click="nextPage(transfer.transferId)">{{getSummary(transfer)}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TransferList",
  data() {
    return {
      transfers : [],
      searchName: '',
      showSearchBar: false,
    }
  },
  created() {
    this.transfers = this.$store.state.transfers
  },
  methods: {
    getSummary(transfer) {
      if(transfer.fromUsername === this.$store.state.user.username) {
        if(transfer.transferType === 2) {
          return transfer.toUsername + " requested $" + transfer.transferAmount + " from you"
        } else if (transfer.transferType === 1) {
          return "you sent " + transfer.toUsername + " $" + transfer.transferAmount + " dollars"
        }
      } else if (transfer.fromUsername !== this.$store.state.username){
        if (transfer.transferType === 2) {
          return "you requested " + " $" + transfer.transferAmount + " dollars"+ " from " + transfer.fromUsername
        } else if (transfer.transferType === 1){
          return transfer.fromUsername + " sent you $" + transfer.transferAmount + " dollars"
        }
      }
    },
    nextPage(transferId) {
      this.$router.push({name : 'transfer-details', params : {transferId: transferId}})
    },
    toggleShowSearchBar() {
      this.showSearchBar = this.showSearchBar !== true;
    }
  },
  computed: {
    filteredList() {
      return this.$store.state.transfers.filter(transfer => {
        return transfer.toUsername.toLowerCase().includes(this.searchName) ||
            transfer.fromUsername.toLowerCase().includes(this.searchName)
      })
    }
  }
}
</script>

<style scoped>
#individual-transfer:hover {
  cursor: pointer;
  font-size: 18px;
}

#individual-transfer {
  margin: 5px;
  border-radius: 5px;
  padding: 5px;
  font-weight: bold;
  color: dimgray;
}

#transfer-item {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 10%;
}

#header {
  margin-top: 8%;
  margin-left: 10%;
  font-weight: bold;
  margin-bottom: 30px;
  font-size: 50px;
}

#sub-header {
  font-size: 14px;
  margin-top: 20px;
  margin-left: 10%;
  font-weight: bold;
}

#empty-array {
  margin-top: 4%;
  margin-left: 15%;
}

#search-bar-toggle {
  font-size: 14px;
  margin-left: 10%;
  font-weight: bold;
  max-width: 18%;
}

#search-bar-toggle:hover {
  font-weight: bolder;
  cursor: pointer;
  font-size: 15px;
}

#or {
  margin-left: 10%;
  font-size: 14px;
}

.filter {
  margin-left: 10%;
  margin-bottom: 30px;
}

</style>
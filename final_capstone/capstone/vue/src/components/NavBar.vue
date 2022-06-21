<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item v-bind:to="{ name: 'home' }">Home</b-nav-item>
        <b-nav-item v-bind:to="{ name: 'pending-transfers'}">Pending Transfers</b-nav-item>

        <b-nav-item-dropdown text="User" right>
          <b-dropdown-item v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</b-dropdown-item>
          <b-dropdown-item v-bind:to="{ name: 'transfers'}">Transfer History</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "NavBar"
}
</script>

<style scoped>

</style>
<template>
  <div>
    <b-navbar type="light" variant="light" fixed="top" id="nav">
      <b-navbar-nav class="ml-1000 !important">
        <b-nav-item v-bind:to="{ name: 'home' }">tenmo</b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="m-auto !important">

      </b-navbar-nav>

      <b-navbar-nav class="mr-0 !important">

      <b-nav-item-dropdown text="user" right>
      <b-dropdown-item v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">logout</b-dropdown-item>
      <b-dropdown-item v-bind:to="{ name: 'transfers'}">transfer history</b-dropdown-item>
      </b-nav-item-dropdown>
        <b-nav-item v-bind:to="{ name: 'pending-transfers'}">pending transfers</b-nav-item>
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
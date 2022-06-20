<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> |
      <router-link v-bind:to="{ name: 'transfers'}">Transfer History</router-link> |
      <router-link v-bind:to="{ name: 'pending-transfers'}">Pending Transfers</router-link>
    </div>
    <router-view />
  </div>
</template>

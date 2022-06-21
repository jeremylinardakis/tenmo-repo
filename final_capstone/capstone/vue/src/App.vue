<template>
  <div id="app">
    <div id="nav">
      <nav-bar></nav-bar>
    </div>
    <router-view id="router-view"/>
  </div>
</template>
<script>
import NavBar from "@/components/NavBar";
export default {
  components: {NavBar}
}
</script>

<style scoped>
#router-view {
  margin-top: 70px;
}
</style>
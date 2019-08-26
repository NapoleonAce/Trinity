<template>
  <div id="app">
    <router-view>
    </router-view>
  </div>
</template>

<script>
  import loginView from "./views/loginView"
  export default {
    name: 'App',
    components: {loginView},
  }

</script>

<style>
#app {
  margin-left: 10%;
  margin-right: 10%;
  /*border:  1px solid #ededed;*/
}
</style>
